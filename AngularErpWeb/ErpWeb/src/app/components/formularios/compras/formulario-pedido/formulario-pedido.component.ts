import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
// Pedido
import { PedidoService } from 'src/app/services/compras/pedido.service';
import { Pedido } from 'src/app/model/entitys/pedido.model';
import { LineaPedido } from 'src/app/model/entitys/linea-pedido.model';
// Articulo
import { ModalArticuloComponent } from 'src/app/components/modales/inventario/modal-articulo/modal-articulo.component';
import { AutocompletarService } from 'src/app/services/autocompletar/autocompletar.service';
// Otros
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import swal from 'sweetalert2';

// jQuery
declare var jQuery: any;


@Component({
  selector: 'app-formulario-pedido',
  templateUrl: './formulario-pedido.component.html',
  styleUrls: ['./formulario-pedido.component.css']
})
export class FormularioPedidoComponent implements OnInit, AfterViewInit {

  public pedido: Pedido;
  private pedidoId: number;
  private pedidoDto: any;
  public tiposImpuesto: string[];
  private respuestaGetCliente: AccionRespuesta;
  public erroresFormulario: Map<string, object>;
  public mapaIva: Map<string, string>;

  // Modal Articulo
  @ViewChild('modalArticulo') modalArticulo: ModalArticuloComponent;

  constructor(
    private pedidoService: PedidoService,
    private autocompletarService: AutocompletarService,
    private router: Router,
    private activateRouter: ActivatedRoute) {

    this.pedido = new Pedido();
    this.pedido.articulosCantidadMap = new Map<number, number>();
    this.pedido.articulosCantidad = {};
    this.pedido.lineaPedido = new Array<LineaPedido>();
    this.tiposImpuesto = ['IVA_GENERAL', 'IVA_REDUCIDO', 'IVA_SUPER_REDUCIDO'];
    this.mapaIva = new Map<string, string>();
    this.rellenaMapaIva();
    this.erroresFormulario = new Map<string, object>();
    this.activateRouter.params.subscribe( params => {
      console.log('Entro al constructor' + params);
      // tslint:disable-next-line: no-string-literal
      this.pedidoId = params['id'];
      if (this.pedidoId != null){
        this.getEditarPedido();
      }
    } );
    this.autocompletarService.paramatroExterno = 'tablaArticulos';
    this.modalArticulo = new ModalArticuloComponent(this.autocompletarService);
    this.modalArticulo.articuloEvento.subscribe( (articulo: any) => {
      console.log('Articulo: ' + JSON.stringify(articulo));
    });
  }

  ngAfterViewInit(): void {
    jQuery.getScript('assets/js/otros/funcionesJS.js').done(() => {
      console.log('Se carga el archivo');
    }).fail(() => {
      console.error('Error, no se ha podido cargar el archivo');
    });
  }

  ngOnInit(): void {

    jQuery('#fechaPedidoDatePicker').datepicker({
      dateFormat: 'dd-mm-yy',
      changeMonth: false,
      changeYear: false,
      dayNames: true,
      showButtonPanel: true,
      onClose: () => {
        this.pedido.fechaPedido = jQuery('#fechaPedidoDatePicker').datepicker('getDate');
      }
    });

    jQuery.getScript('assets/js/datepicker/datepicker-es.js').done(() => {
      console.log('Se carga el español');
    }).fail(() => {
      console.error('Error, no se ha podido cargar el idioma');
    });


  }

  // Metodos del formulario
  public crearPedidoFormulario(): void{

    console.log('Estamos dentro del metodo crearClienteFormulario()');

    this.rellenarPedidoConTablaLineaArticulos();

    console.log('Pedido: ' + JSON.stringify(this.pedido));

    // Si tiene id, llamamos a crear, sino a editar
    if (this.pedido != null && this.pedido.id != null && this.pedido.id !== 0) {

      console.log('Vamos a editar el cliente con ID: ' + this.pedido.id);

      this.pedidoService.actualizarPedido(this.pedido).subscribe( accionRespuesta => {

        this.respuestaCrearEditarPedido(accionRespuesta, true);

      }, (error => {

        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');

      }));

    } else {

      this.pedidoService.crearPedido(this.pedido).subscribe( accionRespuesta => {

        console.log('Vamos a crear el pedido con codigo: ' + this.pedido.codigo);

        this.respuestaCrearEditarPedido(accionRespuesta, false);

      }, (error => {

        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');

      }));

    }
  }

  public getEditarPedido(): void{

    this.pedidoService.getPedido(this.pedidoId).toPromise().then( (accionRespuesta) => {
        try
        {
          console.log('Recuperamos el cliente');

          this.respuestaGetCliente = accionRespuesta;

          if ( this.respuestaGetCliente.resultado )
          {
            console.log('Respuesta: ' +  JSON.stringify(this.respuestaGetCliente.data) );
            console.log('ES: ' + typeof(this.respuestaGetCliente.data));
            // tslint:disable-next-line: no-string-literal
            this.pedidoDto = this.respuestaGetCliente.data['pedidoDto'];
            this.obtenerPedidoDesdePedidoDto(this.pedidoDto);
          }

        }catch (errores){

          console.log('Se ha producido un error al transformar el cliente' + errores);
        }
      }, (error) => {
        console.log('Error, no se ha podido recuperar el cliente' + error);
      }
    );

  }

  private obtenerPedidoDesdePedidoDto(pedidoDto: any): void{

    if ( pedidoDto != null)
    {
      this.pedido.id = pedidoDto.id;
      this.pedido.codigo = pedidoDto.codigo;
      this.pedido.fechaPedido = this.limpiarFecha(pedidoDto.fechaPedido);
      this.pedido.baseImponibleTotal = pedidoDto.baseImponibleTotal;
      this.pedido.impuesto = pedidoDto.impuesto;
      this.pedido.importeTotal = pedidoDto.importeTotal;
      // Limpiamos el pedido previamente
      this.pedido.lineaPedido.pop();
      // Inyectamos el array
      if (pedidoDto.lineasPedidoDto !== 'undefined')
      {
        // tslint:disable-next-line: prefer-const forin prefer-const
        for (let i in pedidoDto.lineasPedidoDto)
        {
          this.pedido.lineaPedido.push(pedidoDto.lineasPedidoDto[i]);
        }
      }
    }
  }

  private respuestaCrearEditarPedido(accionRespuesta: AccionRespuesta, esEditarPedido: boolean): void {

    // console.log('Esta registrado' + accionRespuesta.resultado);
    // console.log('Datos que nos devuelve spring: ' + JSON.stringify(accionRespuesta));
    // Si el resultado es true, navegamos hasta la vista
    if (accionRespuesta.resultado && accionRespuesta.id !== null ) {

      this.router.navigate(['pedidos', 'pedido', accionRespuesta.id]);

      if (esEditarPedido != null && esEditarPedido ){

        swal('Pedido editado', 'Se ha editado el pedido correctamente', 'success');

       }else{

        swal('Nuevo pedido', 'Se ha creado el pedido correctamente', 'success');

       }

    }else{
      // Error
      if ( accionRespuesta != null && accionRespuesta.data != null && accionRespuesta.data !=  null )
      {
        this.erroresFormulario = accionRespuesta.data;
      }else
      {
        swal('Error', 'Se ha producido un error al guardar los datos del pedido', 'error');
      }
    }

  }

  private limpiarFecha(fechaStr: string): Date{

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

  private rellenaMapaIva(): void{
    this.mapaIva.set('IVA_GENERAL', 'GENERAL (21%)');
    this.mapaIva.set('IVA_REDUCIDO', 'REDUCIDO (10%)');
    this.mapaIva.set('IVA_SUPER_REDUCIDO', 'SUPER REDUCIDO (4%)');
  }

  private rellenarPedidoConTablaLineaArticulos(): void{
    // Recuperamos e introducimoos las lineas en un mapa auxiliar
    this.recuperarCeldas();
    // Introducimos el mapa en un objeto para ser enviado
    this.convierteMapaEnObjecto();
  }

  private recuperarCeldas(): void{
    // Primero recuperamos las filas
    const filas = jQuery('#tablaArticulos').find('tr');
    // Recorremos las filas
    // tslint:disable-next-line: prefer-for-of
    for (let i = 0; i < filas.length; i++)
    {
      // Recuperamos las celdas
      const celdas = jQuery(filas[i]).find('td');
      // Obtenemos las celdas de articulo y cantidad
      const celdaArticuloId = jQuery(celdas[0]).text(); // Celda 0 es articuloId..
      const celdaCantidad = jQuery(celdas[6]).text(); // Celda 6 es la cantidad..
      if ( celdaArticuloId != null && celdaArticuloId !== 'undefined' && celdaArticuloId.trim() !== '')
      {
        this.pedido.articulosCantidadMap.set(celdaArticuloId, celdaCantidad);
      }
    }
  }

  private convierteMapaEnObjecto(): void{
    // Convertimos el mapa en object
    this.pedido.articulosCantidadMap.forEach((value, key) => {
      this.pedido.articulosCantidad[key] = value;
    });
  }

  public modalAnadirArticulo(){
    console.log('Entro');
    this.modalArticulo.mostrarModalCrearArticulo();
  }

  public destruirLineaArticulo(id: any){
    if (id != null && id !== 'undefined')
    {
      const lineaArticuloId = 'linea_art_id_' + id;
      jQuery('#' + lineaArticuloId).remove();
    }
    else
    {
      swal('Error', 'Error, no se puede eliiminar la fila, inténtalo mas tarde', 'error');
    }
  }

}
