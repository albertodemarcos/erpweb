import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { FacturaService } from 'src/app/services/ventas/factura.service';
import { Factura } from 'src/app/model/entitys/factura.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import swal from 'sweetalert2';


declare var jQuery: any;

@Component({
  selector: 'app-formulario-factura',
  templateUrl: './formulario-factura.component.html',
  styleUrls: ['./formulario-factura.component.css']
})
export class FormularioFacturaComponent implements OnInit {

  public factura: Factura;
  public tiposImpuesto: string[];
  private facturaId: number;
  private facturaDto: any;
  private respuestaGetFactura: AccionRespuesta;
  public erroresFormulario: Map<string, object>;
  public mapaIva: Map<string, string>;

  constructor(private facturaService: FacturaService, private router: Router, private activateRouter: ActivatedRoute) {
    this.factura = new Factura();
    this.erroresFormulario = new Map<string, object>();
    this.tiposImpuesto = ['IVA_GENERAL', 'IVA_REDUCIDO', 'IVA_SUPER_REDUCIDO'];
    this.mapaIva = new Map<string, string>();
    this.rellenaMapaIva();
    this.activateRouter.params.subscribe( params => {
      console.log('Entro al constructor' + params);
      // tslint:disable-next-line: no-string-literal
      this.facturaId = params['id'];
      if (this.facturaId != null){
        this.getEditarFactura();
      }
    });
   }

  ngOnInit(): void {

  }

  // Metodos del formulario
  public crearFacturaFormulario(): void {

    console.log('Estamos dentro del metodo crearFacturaFormulario()');

    // Si tiene id, llamamos a crear, sino a editar
    if (this.factura != null && this.factura.id != null && this.factura.id !== 0) {

      console.log('Vamos a editar la factura con ID: ' + this.factura.id);

      this.facturaService.actualizarFactura(this.factura).subscribe( accionRespuesta => {

        this.respuestaCrearEditarFactura(accionRespuesta, true);

      }, (error => {

        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');

      }));

    } else {

      this.facturaService.crearFactura(this.factura).subscribe( accionRespuesta => {

        console.log('Vamos a crear la factura con codigo: ' + this.factura.codigo);

        this.respuestaCrearEditarFactura(accionRespuesta, false);

      }, (error => {

        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');

      }));
    }

  }

  getEditarFactura() {

    this.facturaService.getFactura(this.facturaId).toPromise().then( (accionRespuesta) => {
        try
        {
          console.log('Recuperamos la factura');

          this.respuestaGetFactura = accionRespuesta;

          if ( this.respuestaGetFactura.resultado )
          {
            console.log('Respuesta: ' +  JSON.stringify(this.respuestaGetFactura.data) );
            console.log('ES: ' + typeof(this.respuestaGetFactura.data));
            // tslint:disable-next-line: no-string-literal
            this.facturaDto = this.respuestaGetFactura.data['facturaDto'];
            this.obtenerFacturaDesdeFacturaDto(this.facturaDto);
          }

        }catch (errores){

          console.log('Se ha producido un error al transformar la factura' + errores);
        }
      }, (error) => {
        console.log('Error, no se ha podido recuperar la factura' + error);
      }
    );
  }

  obtenerFacturaDesdeFacturaDto(facturaDto: any): void{

    if ( facturaDto != null)
    {
      this.factura.id = facturaDto.id;
      this.factura.codigo = facturaDto.codigo;
      this.factura.fechaCreacion = this.limpiarFecha(facturaDto.fechaCreacion);
      this.factura.fechaInicio = this.limpiarFecha(facturaDto.fechaInicio);
      this.factura.fechaFin = this.limpiarFecha(facturaDto.fechaFin);
      this.factura.descripcion = facturaDto.descripcion;
      this.factura.baseImponible = facturaDto.baseImponible;
      this.factura.impuesto = facturaDto.impuesto;
      this.factura.importeTotal = facturaDto.importeTotal;
    }
  }

  respuestaCrearEditarFactura(accionRespuesta: AccionRespuesta, esEditarFactura: boolean): void {

    console.log('Esta registrado' + accionRespuesta.resultado);
    console.log('Datos que nos devuelve spring: ' + JSON.stringify(accionRespuesta));
    // Si el resultado es true, navegamos hasta la vista
    if (accionRespuesta.resultado && accionRespuesta.id !== null ) {

      this.router.navigate(['facturas', 'factura', accionRespuesta.id]);

      if (esEditarFactura != null && esEditarFactura ){

        swal('Factura editada', 'Se ha editado la factura correctamente', 'success');

       }else{

        swal('Nuevo factura', 'Se ha creado la factura correctamente', 'success');

       }

    }else{

      if ( accionRespuesta != null && accionRespuesta.data != null && accionRespuesta.data !=  null )
      {
        this.erroresFormulario = accionRespuesta.data;
      }else
      {
        swal('Error', 'Se ha producido un error al guardar los datos de la factura', 'error');
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

  rellenaMapaIva(): void {
    this.mapaIva.set('IVA_GENERAL', 'GENERAL');
    this.mapaIva.set('IVA_REDUCIDO', 'REDUCIDO');
    this.mapaIva.set('IVA_SUPER_REDUCIDO', 'SUPER REDUCIDO');
  }

}
