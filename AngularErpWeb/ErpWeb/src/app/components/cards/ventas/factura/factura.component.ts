import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FacturaService } from 'src/app/services/ventas/factura.service';
import { Factura } from 'src/app/model/entitys/factura.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import swal from 'sweetalert2';


@Component({
  selector: 'app-factura',
  templateUrl: './factura.component.html',
  styleUrls: ['./factura.component.css']
})
export class FacturaComponent implements OnInit {

  public factura: Factura;
  private facturaDto: any;
  private facturaId: number;
  private respuestaGetFactura: AccionRespuesta;

  constructor(private facturaService: FacturaService, private router: Router, private activateRouter: ActivatedRoute) {

    this.facturaId = 0;
    this.factura = new Factura();

    this.activateRouter.params.subscribe( params => {
      console.log('Entro al constructor' + params);
      // tslint:disable-next-line: no-string-literal
      this.facturaId = params['id'];
      this.getFactura();
    } );
  }

  getFactura(): void{

    this.facturaService.getFactura(this.facturaId).toPromise().then( (facturaDto) => {
      try
      {
        console.log('Recuperamos el Factura');

        this.respuestaGetFactura = facturaDto;

        if ( this.respuestaGetFactura.resultado )
        {
        console.log('Respuesta: ' +  JSON.stringify(this.respuestaGetFactura.data) );
        console.log('ES: ' + typeof(this.respuestaGetFactura.data));
        // tslint:disable-next-line: no-string-literal
        this.facturaDto = this.respuestaGetFactura.data['facturaDto'];
        this.obtenerFacturaDesdeFacturaDto(this.facturaDto);
        }

      }catch (errores){

        console.log('Se ha producido un error al transformar el Factura' + errores);
      }
      }, (error) => {
      console.log('Error, no se ha podido recuperar el Factura' + error);
      }
    );
  }

  obtenerFacturaDesdeFacturaDto(facturaDto: any): void{

    if ( facturaDto != null)
    {
      this.factura.id = facturaDto.id;
      this.factura.codigo = facturaDto.codigo;
      this.factura.fechaCreacion = facturaDto.fechaCreacion;
      this.factura.fechaInicio = facturaDto.fechaInicio;
      this.factura.fechaFin = facturaDto.fechaFin;
      this.factura.descripcion = facturaDto.descripcion;
      this.factura.baseImponible = facturaDto.baseImponible;
      this.factura.impuesto = facturaDto.impuesto;
      this.factura.importeTotal = facturaDto.importeTotal;
    }
  }

  editarFactura(facturaId: number): void{
    console.log('Factura CON ID: ' + facturaId);
    this.router.navigate(['facturas', 'editar-factura', facturaId]);
  }

  borrarFactura(facturaId: number): void{

    console.log('Factura CON ID: ' + facturaId);

    // Evitamos borrar accidentalmente un Factura
    swal({
      title: 'Eliminar Factura',
      text: '¿Desea eliminar definitivamente esta factura?',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí',
      cancelButtonText: 'No'
    }).then( (resultado) => {
      // Si se pulsa en cancelar, no se continua
      if (!resultado.value) {
        return;
      }

      // Llamamos al servicio de Facturas para eliminar el Factura
      this.facturaService.eliminarFactura(facturaId).toPromise().then( (accionRespuesta) => {

        // Si se ha eliminado correctamente
        if ( accionRespuesta.resultado ) {
        console.log('Se ha eliminado correctamente el Factura');
        swal('Factura eliminada', 'Se ha eliminado el Factura correctamente', 'success').then(() => {
          this.router.navigate( ['facturas'] );
        });

        } else {
        console.log('Se ha producido un error al eliminar el Factura');
        swal('Error', 'El Factura no ha podido ser eliminado', 'error');
        }

      }, (errores) => {
        console.log('Se ha producido un error al eliminar el Factura');
        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');
      } );
    } );

  }

  ngOnInit(): void {
  }

}
