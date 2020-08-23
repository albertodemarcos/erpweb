import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { VentaService } from 'src/app/services/ventas/venta.service';
import { Venta } from 'src/app/model/entitys/venta.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import swal from 'sweetalert2';


@Component({
  selector: 'app-venta',
  templateUrl: './venta.component.html',
  styleUrls: ['./venta.component.css']
})
export class VentaComponent implements OnInit {

  public venta: Venta;
  private ventaDto: any;
  private ventaId: number;
  private respuestaGetVenta: AccionRespuesta;

  constructor(private ventaService: VentaService, private router: Router, private activateRouter: ActivatedRoute) {

    this.ventaId = 0;
    this.venta = new Venta();

    this.activateRouter.params.subscribe( params => {
      console.log('Entro al constructor' + params);
      // tslint:disable-next-line: no-string-literal
      this.ventaId = params['id'];
      this.getVenta();
    } );
  }

  getVenta(): void{

    this.ventaService.getVenta(this.ventaId).toPromise().then( (ventaDto) => {
      try
      {
        console.log('Recuperamos el Venta');

        this.respuestaGetVenta = ventaDto;

        if ( this.respuestaGetVenta.resultado )
        {
        console.log('Respuesta: ' +  JSON.stringify(this.respuestaGetVenta.data) );
        console.log('ES: ' + typeof(this.respuestaGetVenta.data));
        // tslint:disable-next-line: no-string-literal
        this.ventaDto = this.respuestaGetVenta.data['ventaDto'];
        this.obtenerVentaDesdeVentaDto(this.ventaDto);
        }

      }catch (errores){

        console.log('Se ha producido un error al transformar el Venta' + errores);
      }
      }, (error) => {
      console.log('Error, no se ha podido recuperar el Venta' + error);
      }
    );
  }


  obtenerVentaDesdeVentaDto(ventaDto: any): void{

    if ( ventaDto != null)
    {
      this.venta.id = ventaDto.id;
      this.venta.codigo = ventaDto.codigo;
      this.venta.fechaCreacion = ventaDto.fechaCreacion;
      this.venta.fechaInicio = ventaDto.fechaInicio;
      this.venta.fechaFin = ventaDto.fechaFin;
      this.venta.descripcion = ventaDto.descripcion;
      this.venta.baseImponibleTotal = ventaDto.baseImponibleTotal;
      this.venta.impuesto = ventaDto.impuesto;
      this.venta.importeTotal = ventaDto.importeTotal;
    }
  }

  editarVenta(ventaId: number): void{
    console.log('Venta CON ID: ' + ventaId);
    this.router.navigate(['ventas', 'editar-venta', ventaId]);
  }

  borrarVenta(ventaId: number): void{

    console.log('Venta CON ID: ' + ventaId);

    // Evitamos borrar accidentalmente un Venta
    swal({
      title: 'Eliminar Venta',
      text: '¿Desea eliminar definitivamente este Venta?',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí',
      cancelButtonText: 'No'
    }).then( (resultado) => {
      // Si se pulsa en cancelar, no se continua
      if (!resultado.value) {
        return;
      }

      // Llamamos al servicio de Ventas para eliminar el Venta
      this.ventaService.eliminarVenta(ventaId).toPromise().then( (accionRespuesta) => {

        // Si se ha eliminado correctamente
        if ( accionRespuesta.resultado ) {
        console.log('Se ha eliminado correctamente el Venta');
        swal('Venta eliminada', 'Se ha eliminado el Venta correctamente', 'success').then(() => {
          this.router.navigate( ['ventas'] );
        });

        } else {
        console.log('Se ha producido un error al eliminar el Venta');
        swal('Error', 'El Venta no ha podido ser eliminado', 'error');
        }

      }, (errores) => {
        console.log('Se ha producido un error al eliminar el Venta');
        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');
      } );
    } );

  }

  ngOnInit(): void {
  }

}
