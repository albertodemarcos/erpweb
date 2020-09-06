import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FacturaService } from 'src/app/services/ventas/factura.service';
import { Factura } from 'src/app/model/entitys/factura.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import swal from 'sweetalert2';
import { LineaFactura } from '../../../../model/entitys/linea-factura.model';
import { Articulo } from 'src/app/model/entitys/articulo.model';


@Component({
  selector: 'app-factura',
  templateUrl: './factura.component.html',
  styleUrls: ['./factura.component.css']
})
export class FacturaComponent implements OnInit {

  public factura: Factura;
  private facturaDto: any;
  private facturaId: number;
  public tiposImpuesto: string[];
  public mapaIva: Map<string, string>;
  private respuestaGetFactura: AccionRespuesta;

  constructor(
    private facturaService: FacturaService,
    private router: Router,
    private activateRouter: ActivatedRoute
    ) {

    this.facturaId = 0;
    this.factura = new Factura();
    this.factura.lineaFactura = new Array<LineaFactura>();
    this.tiposImpuesto = ['IVA_GENERAL', 'IVA_REDUCIDO', 'IVA_SUPER_REDUCIDO'];
    this.mapaIva = new Map<string, string>();
    this.rellenaMapaIva();
    this.activateRouter.params.subscribe( params => {
      console.log('Entro al constructor' + params);
      // tslint:disable-next-line: no-string-literal
      this.facturaId = params['id'];
      this.getFactura();
    } );
  }

  public getFactura(): void{

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

  private obtenerFacturaDesdeFacturaDto(facturaDto: any): void{

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
      // Lineas de factura
      this.rellenarLineasCompra(facturaDto.lineasFacturaDto);
    }
  }

  private rellenarLineasCompra(lineasFacturaDto: any) {

    if (lineasFacturaDto != null)
    {
      // tslint:disable-next-line: prefer-const
      for (let lineaDto of lineasFacturaDto )
      {
        // tslint:disable-next-line: prefer-const
        let lineaFactura = new LineaFactura();
        // Linea de compra
        lineaFactura.id = lineaDto.id;
        lineaFactura.compraId = lineaDto.compraId;
        lineaFactura.baseImponible = lineaDto.baseImponible;
        lineaFactura.importeTotal = lineaDto.importeTotal;
        lineaFactura.cantidad = lineaDto.cantidad;
        // Articulo
        lineaFactura.articuloDto = new Articulo();
        lineaFactura.articuloDto.id = lineaDto.articuloDto.id;
        lineaFactura.articuloDto.codigo = lineaDto.articuloDto.codigo;
        lineaFactura.articuloDto.nombre = lineaDto.articuloDto.nombre;
        lineaFactura.articuloDto.baseImponible = lineaDto.articuloDto.baseImponible;
        lineaFactura.articuloDto.impuesto = lineaDto.articuloDto.impuesto;
        lineaFactura.articuloDto.importeTotal = lineaDto.articuloDto.importeTotal;
        // Añadir la linea de compra
        this.factura.lineaFactura.push(lineaFactura);
      }
    }
  }

  public editarFactura(facturaId: number): void{
    console.log('Factura CON ID: ' + facturaId);
    this.router.navigate(['facturas', 'editar-factura', facturaId]);
  }

  public borrarFactura(facturaId: number): void{

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

  private rellenaMapaIva(): void{
    this.mapaIva.set('IVA_GENERAL', 'GENERAL (21%)');
    this.mapaIva.set('IVA_REDUCIDO', 'REDUCIDO (10%)');
    this.mapaIva.set('IVA_SUPER_REDUCIDO', 'SUPER REDUCIDO (4%)');
  }

  ngOnInit(): void {
  }

}
