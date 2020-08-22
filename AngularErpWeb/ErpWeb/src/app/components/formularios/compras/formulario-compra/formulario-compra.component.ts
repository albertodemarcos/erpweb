import { Component, OnInit, Input, ViewChild } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';



// Compra
import { CompraService } from 'src/app/services/compras/compra.service';
import { Compra } from 'src/app/model/entitys/compra.model';

// Articulo
import { ModalArticuloComponent } from 'src/app/components/modales/inventario/modal-articulo/modal-articulo.component';
import { AutocompletarService } from 'src/app/services/autocompletar/autocompletar.service';

// Otros
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import swal from 'sweetalert2';


declare var jQuery: any;


@Component({
  selector: 'app-formulario-compra',
  templateUrl: './formulario-compra.component.html',
  styleUrls: ['./formulario-compra.component.css']
})
export class FormularioCompraComponent implements OnInit {

  public compra: Compra;
  private compraId: number;
  private compraDto: any;
  public tiposImpuesto: string[];
  private respuestaGetCompra: AccionRespuesta;
  public erroresFormulario: Map<string, object>;
  public mapaIva: Map<string, string>;

  // Modal Articulo
  // @Input() modalArticulo: ModalArticuloComponent;
  @ViewChild('modalArt1') modalArticulo: ModalArticuloComponent;

  constructor(
    private compraService: CompraService,
    private autocompletarService: AutocompletarService,
    private router: Router,
    private activateRouter: ActivatedRoute) {

    this.compra = new Compra();
    this.tiposImpuesto = ['IVA_GENERAL', 'IVA_REDUCIDO', 'IVA_SUPER_REDUCIDO'];
    this.mapaIva = new Map<string, string>();
    this.rellenaMapaIva();
    this.erroresFormulario = new Map<string, object>();
    this.activateRouter.params.subscribe( params => {
      console.log('Entro al constructor' + params);
      // tslint:disable-next-line: no-string-literal
      this.compraId = params['id'];
      if (this.compraId != null){
        this.getEditarCompra();
      }
    } );
    this.autocompletarService.paramatroExterno = 'tablaArticulos';
    this.modalArticulo = new ModalArticuloComponent(this.autocompletarService);
    this.modalArticulo.articuloEvento.subscribe( articulo => {
      console.log('Articulo: ' + JSON.stringify(articulo));
    });

  }

  ngOnInit(): void {

    /*jQuery('#' + this.idDatePicker).datepicker({
      dateFormat: 'dd-mm-yy',
      changeMonth: false,
      changeYear: false,
      dayNames: true,
      duration: 'slow',
      onClose: () => {
        let fechaCompraTexto = jQuery('#' + this.idDatePicker).val();
        this.compra.fechaCompra = new Date(fechaCompraTexto);
      }
    });

    jQuery.getScript('assets/js/datepicker/datepicker-es.js').done(() => {
      console.log('Se carga el español');
    }).fail(() => {
      console.error('Error, no se ha podido cargar el idioma');
    });*/

  }

  // Metodos del formulario
  public crearCompraFormulario(): void {

    console.log('Estamos dentro del metodo crearCompraFormulario()');

    // Si tiene id, llamamos a crear, sino a editar
    if (this.compra != null && this.compra.id != null && this.compra.id !== 0) {

      console.log('Vamos a editar la compra con ID: ' + this.compra.id);

      this.compraService.actualizarCompra(this.compra).subscribe( accionRespuesta => {

        this.respuestaCrearEditarCompra(accionRespuesta, true);

      }, (error => {

        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');

      }));

    } else {

      this.compraService.crearCompra(this.compra).subscribe( accionRespuesta => {

        console.log('Vamos a crear la compra con codigo: ' + this.compra.codigo);

        this.respuestaCrearEditarCompra(accionRespuesta, false);

      }, (error => {

        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');

      }));

    }

  }

  getEditarCompra() {

    this.compraService.getCompra(this.compraId).toPromise().then( (accionRespuesta) => {
        try
        {
          console.log('Recuperamos la compra');

          this.respuestaGetCompra = accionRespuesta;

          if ( this.respuestaGetCompra.resultado )
          {
            console.log('Respuesta: ' +  JSON.stringify(this.respuestaGetCompra.data) );
            console.log('ES: ' + typeof(this.respuestaGetCompra.data));
            // tslint:disable-next-line: no-string-literal
            this.compraDto = this.respuestaGetCompra.data['compraDto'];
            this.obtenerCompraDesdeCompraDto(this.compraDto);
          }

        }catch (errores){

          console.log('Se ha producido un error al transformar la compra' + errores);
        }
      }, (error) => {
        console.log('Error, no se ha podido recuperar la compra' + error);
      }
    );
  }

  obtenerCompraDesdeCompraDto(compraDto: any): void{

    if ( compraDto != null)
    {
      this.compra.id = compraDto.id;
      this.compra.codigo = compraDto.codigo;
      this.compra.fechaCompra = this.limpiarFecha(compraDto.fechaCompra);
      this.compra.articulo = compraDto.articulo;
      this.compra.cantidad = compraDto.cantidad;
      this.compra.baseImponibleTotal = compraDto.baseImponibleTotal;
      this.compra.impuesto = compraDto.impuesto;
      this.compra.importeTotal = compraDto.importeTotal;
    }
  }

  respuestaCrearEditarCompra(accionRespuesta: AccionRespuesta, esEditarCompra: boolean): void {

    console.log('Esta registrado' + accionRespuesta.resultado);
    console.log('Datos que nos devuelve spring: ' + JSON.stringify(accionRespuesta));
    // Si el resultado es true, navegamos hasta la vista
    if (accionRespuesta.resultado && accionRespuesta.id !== null ) {

      if (esEditarCompra != null && esEditarCompra ){

        swal('Compra editado', 'Se ha editado la compra correctamente', 'success');

        this.router.navigate(['compras', 'compra', accionRespuesta.id]);

       }else{

        swal('Nueva compra', 'Se ha creado la compra correctamente', 'success');

        this.router.navigate(['compras', 'compra', accionRespuesta.id]);
       }

    }else{
      // Error
      if ( accionRespuesta != null && accionRespuesta.data != null )
      {
        this.erroresFormulario = accionRespuesta.data;

      }else
      {
        swal('Error', 'Se ha producido un error al guardar los datos de la compra', 'error');
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

  modalAnadirArticulo(){
    console.log('Entro');
    this.modalArticulo.mostrarModalCrearArticulo();
  }


} // compra.fechaCompra = $event
