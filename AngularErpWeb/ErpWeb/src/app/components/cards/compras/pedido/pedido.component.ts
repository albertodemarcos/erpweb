import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { PedidoService } from 'src/app/services/compras/pedido.service';
import { Pedido } from 'src/app/model/entitys/pedido.model';
import { LineaPedido } from 'src/app/model/entitys/linea-pedido.model';
import { Articulo } from 'src/app/model/entitys/articulo.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import swal from 'sweetalert2';



@Component({
  selector: 'app-pedido',
  templateUrl: './pedido.component.html',
  styleUrls: ['./pedido.component.css']
})
export class PedidoComponent implements OnInit {

  public pedido: Pedido;
  private pedidoDto: any;
  private pedidoId: number;
  public tiposImpuesto: string[];
  public mapaIva: Map<string, string>;
  private respuestaGetPedido: AccionRespuesta;

  constructor(private pedidoService: PedidoService, private router: Router, private activateRouter: ActivatedRoute) {

    this.pedidoId = 0;
    this.pedido = new Pedido();
    this.pedido.lineaPedido = new Array<LineaPedido>();
    this.tiposImpuesto = ['IVA_GENERAL', 'IVA_REDUCIDO', 'IVA_SUPER_REDUCIDO'];
    this.mapaIva = new Map<string, string>();
    this.rellenaMapaIva();
    this.activateRouter.params.subscribe( params => {
      console.log('Entro al constructor' + params);
      // tslint:disable-next-line: no-string-literal
      this.pedidoId = params['id'];
      this.getPedido();
    } );
  }

  public getPedido(): void{

    this.pedidoService.getPedido(this.pedidoId).toPromise().then( (pedidoDto) => {
      try
      {
        console.log('Recuperamos el Pedido');

        this.respuestaGetPedido = pedidoDto;

        if ( this.respuestaGetPedido.resultado )
        {
          // console.log('Respuesta: ' +  JSON.stringify(this.respuestaGetPedido.data) );
          // console.log('ES: ' + typeof(this.respuestaGetPedido.data));
          // tslint:disable-next-line: no-string-literal
          this.pedidoDto = this.respuestaGetPedido.data['pedidoDto'];
          this.obtenerPedidoDesdePedidoDto(this.pedidoDto);
        }

      }catch (errores){
        console.log('Se ha producido un error al transformar el Pedido' + errores);
      }
      }, (error) => {
      console.log('Error, no se ha podido recuperar el Pedido' + error);
      }
    );
  }

  private obtenerPedidoDesdePedidoDto(pedidoDto: any): void{

    if ( pedidoDto != null)
    {
      this.pedido.id = pedidoDto.id;
      this.pedido.codigo = pedidoDto.codigo;
      this.pedido.fechaPedido = pedidoDto.fechaPedido;
      this.pedido.cantidad = pedidoDto.cantidad;
      this.pedido.baseImponibleTotal = pedidoDto.baseImponibleTotal;
      this.pedido.importeTotal = pedidoDto.importeTotal;
      // Lineas de pedido
      this.rellenarLineasPedido(pedidoDto.lineasPedidoDto);
    }
  }

  private rellenarLineasPedido(lineasPedidoDto: any) {

    if (lineasPedidoDto != null)
    {
      // tslint:disable-next-line: prefer-const
      for (let lineaDto of lineasPedidoDto )
      {
        // tslint:disable-next-line: prefer-const
        let lineaPedido = new LineaPedido();
        // Linea de compra
        lineaPedido.id = lineaDto.id;
        lineaPedido.compraId = lineaDto.compraId;
        lineaPedido.baseImponible = lineaDto.baseImponible;
        lineaPedido.importeTotal = lineaDto.importeTotal;
        lineaPedido.cantidad = lineaDto.cantidad;
        // Articulo
        lineaPedido.articuloDto = new Articulo();
        lineaPedido.articuloDto.id = lineaDto.articuloDto.id;
        lineaPedido.articuloDto.codigo = lineaDto.articuloDto.codigo;
        lineaPedido.articuloDto.nombre = lineaDto.articuloDto.nombre;
        lineaPedido.articuloDto.baseImponible = lineaDto.articuloDto.baseImponible;
        lineaPedido.articuloDto.impuesto = lineaDto.articuloDto.impuesto;
        lineaPedido.articuloDto.importeTotal = lineaDto.articuloDto.importeTotal;
        // Añadir la linea de compra
        this.pedido.lineaPedido.push(lineaPedido);
      }
    }

  }

  public editarPedido(pedidoId: number): void{
    console.log('Pedido CON ID: ' + pedidoId);
    this.router.navigate(['pedidos', 'editar-pedido', pedidoId]);
  }

  public borrarPedido(pedidoId: number): void{

    console.log('Pedido CON ID: ' + pedidoId);

    // Evitamos borrar accidentalmente un Pedido
    swal({
      title: 'Eliminar Pedido',
      text: '¿Desea eliminar definitivamente este Pedido?',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí',
      cancelButtonText: 'No'
    }).then( (resultado) => {
      // Si se pulsa en cancelar, no se continua
      if (!resultado.value) {
        return;
      }

      // Llamamos al servicio de Pedidos para eliminar el Pedido
      this.pedidoService.eliminarPedido(pedidoId).toPromise().then( (accionRespuesta) => {

        // Si se ha eliminado correctamente
        if ( accionRespuesta.resultado ) {
        console.log('Se ha eliminado correctamente el Pedido');
        swal('Pedido eliminado', 'Se ha eliminado el Pedido correctamente', 'success').then(() => {
          this.router.navigate( ['pedidos'] );
        });

        } else {
        console.log('Se ha producido un error al eliminar el Pedido');
        swal('Error', 'El Pedido no ha podido ser eliminado', 'error');
        }

      }, (errores) => {
        console.log('Se ha producido un error al eliminar el Pedido');
        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');
      } );
    } );

  }

  public convertirPedidoCompra(pedidoId: number) {
    console.log('Pedido CON ID: ' + pedidoId);

    this.pedidoService.crearCompraDePedido(pedidoId).toPromise().then(
      (accionRespuesta) => {
        try
        {
          console.log('Compra: ' + JSON.stringify(accionRespuesta) );
          const compraId = accionRespuesta.id;
          if (accionRespuesta.resultado && compraId != null && compraId > 0)
          {
            // Despues de convertir el pedido, vamos a la compra
            this.router.navigate(['compras', 'compra', compraId]);
            swal('Compra', 'Se ha creado una compra desde un correctamente', 'success');
            return;
          }
          swal('Error', 'Error, se ha producido un error al redireccinar', 'error');
        } catch (errores)
        {
          swal('Error', 'Error, no se ha podido convertir en compra', 'error');
        }
      }, (error) => {
        console.log('Error, no se ha podido convetir el pedido en compra' + error);
        swal('Error', 'Error, no se ha podido convertir en compra: ' + error, 'error');
      }
    );
  }

  private rellenaMapaIva(): void{
    this.mapaIva.set('IVA_GENERAL', 'GENERAL (21%)');
    this.mapaIva.set('IVA_REDUCIDO', 'REDUCIDO (10%)');
    this.mapaIva.set('IVA_SUPER_REDUCIDO', 'SUPER REDUCIDO (4%)');
  }

  ngOnInit(): void {
  }

}
