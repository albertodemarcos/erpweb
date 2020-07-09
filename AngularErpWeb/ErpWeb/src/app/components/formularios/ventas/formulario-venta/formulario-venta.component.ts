import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { VentaService } from 'src/app/services/ventas/venta.service';
import { Venta } from 'src/app/model/entitys/venta.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import swal from 'sweetalert2';


declare var jQuery: any;

@Component({
  selector: 'app-formulario-venta',
  templateUrl: './formulario-venta.component.html',
  styleUrls: ['./formulario-venta.component.css']
})
export class FormularioVentaComponent implements OnInit {

  public venta: Venta;
  public tiposImpuesto: string[];
  private ventaId: number;
  private ventaDto: any;
  private respuestaGetVenta: AccionRespuesta;
  public erroresFormulario: Map<string, object>;
  public mapaIva: Map<string, string>;

  constructor(private ventaService: VentaService, private router: Router, private activateRouter: ActivatedRoute) {
    this.venta = new Venta();
    this.erroresFormulario = new Map<string, object>();
    this.tiposImpuesto = ['IVA_GENERAL', 'IVA_REDUCIDO', 'IVA_SUPER_REDUCIDO'];
    this.mapaIva = new Map<string, string>();
    this.rellenaMapaIva();
    this.activateRouter.params.subscribe( params => {
      console.log('Entro al constructor' + params);
      // tslint:disable-next-line: no-string-literal
      this.ventaId = params['id'];
      if (this.ventaId != null){
        this.getEditarVenta();
      }
    });
  }

  ngOnInit(): void {

  }

  // Metodos del formulario

  public crearVentaFormulario(): void {

    console.log('Estamos dentro del metodo crearcontratoFormulario()');

    // Si tiene id, llamamos a crear, sino a editar
    if (this.venta != null && this.venta.id != null && this.venta.id !== 0) {

      console.log('Vamos a editar el contrato con ID: ' + this.venta.id);

      this.ventaService.actualizarVenta(this.venta).subscribe( accionRespuesta => {

        this.respuestaCrearEditarVenta(accionRespuesta, true);

      }, (error => {

        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');

      }));

    } else {

      this.ventaService.crearVenta(this.venta).subscribe( accionRespuesta => {

        console.log('Vamos a crear la venta con codigo: ' + this.venta.codigo);

        this.respuestaCrearEditarVenta(accionRespuesta, false);

      }, (error => {

        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');

      }));
    }

  }

  getEditarVenta() {

    this.ventaService.getVenta(this.ventaId).toPromise().then( (accionRespuesta) => {
        try
        {
          console.log('Recuperamos la venta');

          this.respuestaGetVenta = accionRespuesta;

          if ( this.respuestaGetVenta.resultado )
          {
            console.log('Respuesta: ' +  JSON.stringify(this.respuestaGetVenta.data) );
            console.log('ES: ' + typeof(this.respuestaGetVenta.data));
            // tslint:disable-next-line: no-string-literal
            this.ventaDto = this.respuestaGetVenta.data['ventaDto'];
            this.obtenerVentaDesdeVentaDto(this.ventaDto);
          }

        }catch (errores){

          console.log('Se ha producido un error al transformar la venta' + errores);
        }
      }, (error) => {
        console.log('Error, no se ha podido recuperar la venta' + error);
      }
    );
  }

  obtenerVentaDesdeVentaDto(ventaDto: any): void{

    if ( ventaDto != null)
    {
      this.venta.id = ventaDto.id;
      this.venta.codigo = ventaDto.codigo;
      this.venta.fechaCreacion = this.limpiarFecha(ventaDto.fechaCreacion);
      this.venta.fechaInicio = this.limpiarFecha(ventaDto.fechaInicio);
      this.venta.fechaFin = this.limpiarFecha(ventaDto.fechaFin);
      this.venta.descripcion = ventaDto.descripcion;
      this.venta.baseImponibleTotal = ventaDto.baseImponibleTotal;
      this.venta.impuesto = ventaDto.impuesto;
      this.venta.importeTotal = ventaDto.importeTotal;
    }
  }

  respuestaCrearEditarVenta(accionRespuesta: AccionRespuesta, esEditarVenta: boolean): void {

    console.log('Esta registrado' + accionRespuesta.resultado);
    console.log('Datos que nos devuelve spring: ' + JSON.stringify(accionRespuesta));
    // Si el resultado es true, navegamos hasta la vista
    if (accionRespuesta.resultado && accionRespuesta.id !== null ) {

      this.router.navigate(['ventas', 'venta', accionRespuesta.id]);

      if (esEditarVenta != null && esEditarVenta ){

        swal('Venta editada', 'Se ha editado la venta correctamente', 'success');

       }else{

        swal('Nueva venta', 'Se ha creado la venta correctamente', 'success');

       }

    }else{

      if ( accionRespuesta != null && accionRespuesta.data != null && accionRespuesta.data !=  null )
      {
        this.erroresFormulario = accionRespuesta.data;
      }else
      {
        swal('Error', 'Se ha producido un error al guardar los datos de la venta', 'error');
      }
    }
  }

  limpiarFecha(fechaStr: string): Date{

    if (fechaStr != null && fechaStr.trim() !== ''){
      try {
        const fechaLimpia: Date = new Date(fechaStr);
        return fechaLimpia;
      } catch (error) {
        console.log('Error al convertir la fecha' + error);
      }
    }
    return new Date();
  }

  rellenaMapaIva(): void{
    this.mapaIva.set('IVA_GENERAL', 'GENERAL');
    this.mapaIva.set('IVA_REDUCIDO', 'REDUCIDO');
    this.mapaIva.set('IVA_SUPER_REDUCIDO', 'SUPER REDUCIDO');
  }


}
