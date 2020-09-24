import { Component, OnInit, ViewChild, OnChanges, SimpleChanges, AfterViewInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
// Compra
import { CompraService } from 'src/app/services/compras/compra.service';
import { Compra } from 'src/app/model/entitys/compra.model';
import { LineaCompra } from 'src/app/model/entitys/linea-compra.model';
// Articulo
import { ModalArticuloComponent } from 'src/app/components/modales/inventario/modal-articulo/modal-articulo.component';
import { AutocompletarService } from 'src/app/services/autocompletar/autocompletar.service';
// Otros
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import swal from 'sweetalert2';

// jQuery
declare var jQuery: any;


@Component({
  selector: 'app-formulario-compra',
  templateUrl: './formulario-compra.component.html',
  styleUrls: ['./formulario-compra.component.css']
})
export class FormularioCompraComponent implements OnInit, AfterViewInit {

  public compra: Compra;
  private compraId: number;
  private compraDto: any;
  public tiposImpuesto: string[];
  private respuestaGetCompra: AccionRespuesta;
  public erroresFormulario: Map<string, object>;
  public mapaIva: Map<string, string>;
  public titulo: string;
  public botonTitulo: string;

  // Modal Articulo
  @ViewChild('modalArticulo') modalArticulo: ModalArticuloComponent;

  constructor(
    private compraService: CompraService,
    private autocompletarService: AutocompletarService,
    private router: Router,
    private activateRouter: ActivatedRoute) {

    this.compra = new Compra();
    this.compra.articulosCantidadMap = new Map<number, number>();
    this.compra.articulosCantidad = {};
    this.compra.lineaCompra = new Array<LineaCompra>();
    this.tiposImpuesto = ['IVA_GENERAL', 'IVA_REDUCIDO', 'IVA_SUPER_REDUCIDO'];
    this.mapaIva = new Map<string, string>();
    this.rellenaMapaIva();
    this.erroresFormulario = new Map<string, object>();
    this.titulo = 'Nueva compra';
    this.botonTitulo = 'Crear compra';
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

    jQuery('#fechaCompraDatePicker').datepicker({
      dateFormat: 'dd-mm-yy',
      changeMonth: false,
      changeYear: false,
      dayNames: true,
      showButtonPanel: true,
      onClose: () => {
        this.compra.fechaCompra = jQuery('#fechaCompraDatePicker').datepicker('getDate');
      }
    });

    jQuery.getScript('assets/js/datepicker/datepicker-es.js').done(() => {
      console.log('Se carga el español');
    }).fail(() => {
      console.error('Error, no se ha podido cargar el idioma');
    });
  }

  // Metodos del formulario
  public crearCompraFormulario(): void{

    console.log('Estamos dentro del metodo crearCompraFormulario()');

    this.rellenarCompraConTablaLineaArticulos();

    console.log('Compra: ' + JSON.stringify(this.compra));

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

  public getEditarCompra(): void{

    this.compraService.getCompra(this.compraId).toPromise().then( (accionRespuesta) => {
        try
        {
          this.respuestaGetCompra = accionRespuesta;

          if ( this.respuestaGetCompra.resultado )
          {
            // tslint:disable-next-line: no-string-literal
            this.compraDto = this.respuestaGetCompra.data['compraDto'];
            this.obtenerCompraDesdeCompraDto(this.compraDto);
            this.titulo = 'Editar compra';
            this.botonTitulo = 'Editar compra';
          }

        }catch (errores){

          console.log('Se ha producido un error al transformar la compra' + errores);
        }
      }, (error) => {
        console.log('Error, no se ha podido recuperar la compra' + error);
      }
    );
  }

  private obtenerCompraDesdeCompraDto(compraDto: any): void{

    if ( compraDto != null)
    {
      this.compra.id = compraDto.id;
      this.compra.codigo = compraDto.codigo;
      this.compra.fechaCompra = this.limpiarFecha(compraDto.fechaCompra);
      this.compra.baseImponibleTotal = compraDto.baseImponibleTotal;
      this.compra.impuesto = compraDto.impuesto;
      this.compra.importeTotal = compraDto.importeTotal;
      // Limpiamos la compra previamente
      this.compra.lineaCompra.pop();
      // Inyectamos el array
      if (compraDto.lineasCompraDto !== 'undefined')
      {
        // tslint:disable-next-line: prefer-const forin prefer-const
        for (let i in compraDto.lineasCompraDto)
        {
          this.compra.lineaCompra.push(compraDto.lineasCompraDto[i]);
        }
      }
    }
  }

  private respuestaCrearEditarCompra(accionRespuesta: AccionRespuesta, esEditarCompra: boolean): void {

    // console.log('Esta registrado' + accionRespuesta.resultado);
    // console.log('Datos que nos devuelve spring: ' + JSON.stringify(accionRespuesta));
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

  private rellenarCompraConTablaLineaArticulos(): void{
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
      // const celdaAlmacenId = jQuery(celdas[8]).text(); // Celda 8 es la almacenId..
      if ( celdaArticuloId != null && celdaArticuloId !== 'undefined' && celdaArticuloId.trim() !== '')
      {
        // this.compra.almacenId = celdaAlmacenId;
        this.compra.articulosCantidadMap.set(celdaArticuloId, celdaCantidad);
      }
    }
  }

  private convierteMapaEnObjecto(): void{
    // Convertimos el mapa en object
    this.compra.articulosCantidadMap.forEach((value, key) => {
      this.compra.articulosCantidad[key] = value;
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
