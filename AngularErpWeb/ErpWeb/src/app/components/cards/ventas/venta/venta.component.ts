import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { VentaService } from 'src/app/services/ventas/venta.service';
import { Venta } from 'src/app/model/entitys/venta.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import swal from 'sweetalert2';
import { Articulo } from 'src/app/model/entitys/articulo.model';
import { LineaVenta } from 'src/app/model/entitys/linea-venta.model';


@Component({
  selector: 'app-venta',
  templateUrl: './venta.component.html',
  styleUrls: ['./venta.component.css']
})
export class VentaComponent implements OnInit {

  public venta: Venta;
  private ventaDto: any;
  private ventaId: number;
  public tiposImpuesto: string[];
  public mapaIva: Map<string, string>;
  private respuestaGetVenta: AccionRespuesta;

  constructor(private ventaService: VentaService, private router: Router, private activateRouter: ActivatedRoute) {

    this.ventaId = 0;
    this.venta = new Venta();
    this.venta.lineaVenta = new Array<LineaVenta>();
    this.tiposImpuesto = ['IVA_GENERAL', 'IVA_REDUCIDO', 'IVA_SUPER_REDUCIDO'];
    this.mapaIva = new Map<string, string>();
    this.rellenaMapaIva();
    this.activateRouter.params.subscribe( params => {
      console.log('Entro al constructor' + params);
      // tslint:disable-next-line: no-string-literal
      this.ventaId = params['id'];
      this.getVenta();
    } );
  }

  public getVenta(): void{

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

  private obtenerVentaDesdeVentaDto(ventaDto: any): void{

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
      // Lineas de venta
      this.rellenarLineasVenta(ventaDto.lineasVentaDto);
    }
  }

  private rellenarLineasVenta(lineasCompraDto: any) {

    if (lineasCompraDto != null)
    {
      // tslint:disable-next-line: prefer-const
      for (let lineaDto of lineasCompraDto )
      {
        // tslint:disable-next-line: prefer-const
        let lineaVenta = new LineaVenta();
        // Linea de compra
        lineaVenta.id = lineaDto.id;
        lineaVenta.compraId = lineaDto.compraId;
        lineaVenta.baseImponible = lineaDto.baseImponible;
        lineaVenta.importeTotal = lineaDto.importeTotal;
        lineaVenta.cantidad = lineaDto.cantidad;
        // Articulo
        lineaVenta.articuloDto = new Articulo();
        lineaVenta.articuloDto.id = lineaDto.articuloDto.id;
        lineaVenta.articuloDto.codigo = lineaDto.articuloDto.codigo;
        lineaVenta.articuloDto.nombre = lineaDto.articuloDto.nombre;
        lineaVenta.articuloDto.baseImponible = lineaDto.articuloDto.baseImponible;
        lineaVenta.articuloDto.impuesto = lineaDto.articuloDto.impuesto;
        lineaVenta.articuloDto.importeTotal = lineaDto.articuloDto.importeTotal;
        // Añadir la linea de compra
        this.venta.lineaVenta.push(lineaVenta);
      }
    }
  }

  public editarVenta(ventaId: number): void{
    console.log('Venta CON ID: ' + ventaId);
    this.router.navigate(['ventas', 'editar-venta', ventaId]);
  }

  public borrarVenta(ventaId: number): void{

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

  private rellenaMapaIva(): void{
    this.mapaIva.set('IVA_GENERAL', 'GENERAL (21%)');
    this.mapaIva.set('IVA_REDUCIDO', 'REDUCIDO (10%)');
    this.mapaIva.set('IVA_SUPER_REDUCIDO', 'SUPER REDUCIDO (4%)');
  }



  ngOnInit(): void {
  }

}
