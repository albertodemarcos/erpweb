import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
// StockAlmacen
import { StockService } from 'src/app/services/inventario/stock.service';
import { Stock } from 'src/app/model/entitys/stock.model';
// Otros
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import swal from 'sweetalert2';

@Component({
  selector: 'app-stock',
  templateUrl: './stock.component.html',
  styleUrls: ['./stock.component.css']
})
export class StockComponent implements OnInit {

  public stock: Stock;
  private stockDto: any;
  private stockId: number;
  public tiposImpuestos: string[];
  private respuestaGetArticulo: AccionRespuesta;
  public mapaIva: Map<string, string>;

  constructor(private stockService: StockService, private router: Router, private activateRouter: ActivatedRoute) {

    this.stockId = 0;
    this.stock = new Stock();
    this.tiposImpuestos = ['IVA_GENERAL', 'IVA_REDUCIDO', 'IVA_SUPER_REDUCIDO'];
    this.mapaIva = new Map<string, string>();
    this.rellenaMapaIva();
    this.activateRouter.params.subscribe( params => {
      console.log('Entro al constructor' + params);
      // tslint:disable-next-line: no-string-literal
      this.stockId = params['id'];
      this.getStockArticulo();
    } );
  }

  public getStockArticulo(): void{

    this.stockService.getStockAlmacen(this.stockId).toPromise().then( (stockDto: any) => {
      try
      {
        console.log('Recuperamos el Articulo');

        this.respuestaGetArticulo = stockDto;

        if ( this.respuestaGetArticulo.resultado )
        {
        console.log('Respuesta: ' +  JSON.stringify(this.respuestaGetArticulo.data) );
        console.log('ES: ' + typeof(this.respuestaGetArticulo.data));
        // tslint:disable-next-line: no-string-literal
        this.stockDto = this.respuestaGetArticulo.data['stockArticuloDto'];
        this.obtenerStockArticuloDesdeArticuloDto(this.stockDto);
        }

      }catch (errores){

        console.log('Se ha producido un error al transformar el Articulo' + errores);
      }
      }, (error) => {
      console.log('Error, no se ha podido recuperar el Articulo' + error);
      }
    );
  }

  private obtenerStockArticuloDesdeArticuloDto(stockArticuloDto: any): void{

    if ( stockArticuloDto != null)
    {
      this.stock.id = stockArticuloDto.id;
      this.stock.codigo = stockArticuloDto.codigo;
      // this.stock.articuloId = stockArticuloDto.articuloId;
      // this.stock.almacenId = stockArticuloDto.almacenId;
      this.stock.articulo = stockArticuloDto.articuloDto;
      this.stock.almacen = stockArticuloDto.almacenDto;
      this.stock.cantidad = stockArticuloDto.cantidad;
    }
  }

  public rellenaMapaIva(): void{
    this.mapaIva.set('IVA_GENERAL', 'GENERAL');
    this.mapaIva.set('IVA_REDUCIDO', 'REDUCIDO');
    this.mapaIva.set('IVA_SUPER_REDUCIDO', 'SUPER REDUCIDO');
  }

  public editarStockArticulo(articuloId: number): void{
    console.log('Articulo CON ID: ' + articuloId);
    this.router.navigate(['stock', 'editar-stock', articuloId]);
  }

  public borrarStockArticulo(stockId: number): void{

    console.log('Stock CON ID: ' + stockId);

    // Evitamos borrar accidentalmente un Articulo
    swal({
      title: 'Eliminar Articulo',
      text: '¿Desea eliminar definitivamente este Articulo?',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí',
      cancelButtonText: 'No'
    }).then( (resultado) => {
      // Si se pulsa en cancelar, no se continua
      if (!resultado.value) {
        return;
      }

      // Llamamos al servicio de Articulos para eliminar el Articulo
      this.stockService.eliminarStockAlmacen(stockId).toPromise().then( (accionRespuesta) => {

        // Si se ha eliminado correctamente
        if ( accionRespuesta.resultado ) {
          console.log('Se ha eliminado correctamente el stock');
          swal('Stock artículo eliminado', 'Se ha eliminado el stock del artículo correctamente', 'success').then(() => {
            this.router.navigate( ['stock'] );
          });
        }
        else
        {
          console.log('Se ha producido un error al eliminar el stock del Artículo');
          swal('Error', 'El stock del artículo no ha podido ser eliminado', 'error');
        }

      }, (errores) => {
          console.log('Se ha producido un error al eliminar el stock del Artículo');
          swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');
      } );
    } );

  }

  ngOnInit(): void {
  }

}
