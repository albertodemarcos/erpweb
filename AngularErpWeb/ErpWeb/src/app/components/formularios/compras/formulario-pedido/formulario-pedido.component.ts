import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { PedidoService } from 'src/app/services/compras/pedido.service';
import { Pedido } from 'src/app/model/entitys/pedido.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import swal from 'sweetalert2';


declare var jQuery: any;

@Component({
  selector: 'app-formulario-pedido',
  templateUrl: './formulario-pedido.component.html',
  styleUrls: ['./formulario-pedido.component.css']
})
export class FormularioPedidoComponent implements OnInit {

  public pedido: Pedido;
  private idDatePicker: string;
  private pedidoId: number;
  private pedidoDto: any;
  public tiposImpuesto: string[];
  private respuestaGetCliente: AccionRespuesta;
  public erroresFormulario: Map<string, object>;
  public mapaIva: Map<string, string>;

  constructor(private pedidoService: PedidoService, private router: Router, private activateRouter: ActivatedRoute) {
    this.pedido = new Pedido();
    this.idDatePicker = 'fechaPedidoDatePicker';
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
   }

  ngOnInit(): void {

    /*jQuery('#' + this.idDatePicker).datepicker({
      dateFormat: 'dd-mm-yy',
      changeMonth: false,
      changeYear: false,
      dayNames: true,
      duration: 'slow'
    });

    jQuery.getScript('assets/js/datepicker/datepicker-es.js').done(() => {
      console.log('Se carga el español');
    }).fail(() => {
      console.error('Error, no se ha podido cargar el idioma');
    });*/
  }

  // Metodos del formulario
  public crearPedidoFormulario(): void {

    console.log('Estamos dentro del metodo crearClienteFormulario()');

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

  getEditarPedido() {

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


  obtenerPedidoDesdePedidoDto(pedidoDto: any): void{

    if ( pedidoDto != null)
    {
      this.pedido.id = pedidoDto.id;
      this.pedido.codigo = pedidoDto.codigo;
      this.pedido.fechaPedido = this.limpiarFecha(pedidoDto.fechaPedido);
      this.pedido.articulo = pedidoDto.articulo;
      this.pedido.cantidad = pedidoDto.cantidad;
      this.pedido.baseImponibleTotal = pedidoDto.baseImponibleTotal;
      this.pedido.impuesto = pedidoDto.impuesto;
      this.pedido.importeTotal = pedidoDto.importeTotal;
    }
  }

  respuestaCrearEditarPedido(accionRespuesta: AccionRespuesta, esEditarPedido: boolean): void {

    console.log('Esta registrado' + accionRespuesta.resultado);
    console.log('Datos que nos devuelve spring: ' + JSON.stringify(accionRespuesta));
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
