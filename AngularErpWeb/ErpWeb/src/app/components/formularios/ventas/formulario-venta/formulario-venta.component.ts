import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
// Venta
import { VentaService } from 'src/app/services/ventas/venta.service';
import { Venta } from 'src/app/model/entitys/venta.model';
import { LineaVenta } from 'src/app/model/entitys/linea-venta.model';
// Articulo
import { ModalArticuloComponent } from 'src/app/components/modales/inventario/modal-articulo/modal-articulo.component';
import { AutocompletarService } from 'src/app/services/autocompletar/autocompletar.service';
// Otros
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import swal from 'sweetalert2';

// jQuery
declare var jQuery: any;


@Component({
  selector: 'app-formulario-venta',
  templateUrl: './formulario-venta.component.html',
  styleUrls: ['./formulario-venta.component.css']
})
export class FormularioVentaComponent implements OnInit, AfterViewInit {

  public venta: Venta;
  private ventaId: number;
  private ventaDto: any;
  public tiposImpuesto: string[];
  private respuestaGetVenta: AccionRespuesta;
  public erroresFormulario: Map<string, object>;
  public mapaIva: Map<string, string>;
  public titulo: string;
  public botonTitulo: string;

  // Modal Articulo
  @ViewChild('modalArticulo') modalArticulo: ModalArticuloComponent;

  constructor(
    private ventaService: VentaService,
    private autocompletarService: AutocompletarService,
    private router: Router, private activateRouter: ActivatedRoute) {

    this.venta = new Venta();
    this.venta.articulosCantidadMap = new Map<number, number>();
    this.venta.articulosCantidad = {};
    this.venta.lineaVenta = new Array<LineaVenta>();
    this.erroresFormulario = new Map<string, object>();
    this.tiposImpuesto = ['IVA_GENERAL', 'IVA_REDUCIDO', 'IVA_SUPER_REDUCIDO'];
    this.mapaIva = new Map<string, string>();
    this.titulo = 'Nueva venta';
    this.botonTitulo = 'Crear venta';
    this.rellenaMapaIva();
    this.activateRouter.params.subscribe( params => {
      // tslint:disable-next-line: no-string-literal
      this.ventaId = params['id'];
      if (this.ventaId != null){
        this.getEditarVenta();
      }
    });
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

    jQuery('#fechaVentaDatePicker').datepicker({
      dateFormat: 'dd-mm-yy',
      changeMonth: false,
      changeYear: false,
      dayNames: true,
      showButtonPanel: true,
      onClose: () => {
        this.venta.fechaInicio = jQuery('#fechaVentaDatePicker').datepicker('getDate');
      }
    });

    jQuery.getScript('assets/js/datepicker/datepicker-es.js').done(() => {
      console.log('Se carga el español');
    }).fail(() => {
      console.error('Error, no se ha podido cargar el idioma');
    });

  }

  // Metodos del formulario
  public crearVentaFormulario(): void{

    console.log('Estamos dentro del metodo crearcontratoFormulario()');

    this.rellenarVentaConTablaLineaArticulos();

    console.log('Venta: ' + JSON.stringify(this.venta));

    // Si tiene id, llamamos a crear, sino a editar
    if (this.venta != null && this.venta.id != null && this.venta.id !== 0) {

      console.log('Vamos a editar el contrato con ID: ' + this.venta.id);

      this.ventaService.actualizarVenta(this.venta).subscribe( accionRespuesta => {

        this.respuestaCrearEditarVenta(accionRespuesta, true);

      }, (error => {

        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');

      }));

    } else {

      this.ventaService.crearVenta(this.venta).subscribe( accionRespuesta => {

        console.log('Vamos a crear la venta con codigo: ' + this.venta.codigo);

        this.respuestaCrearEditarVenta(accionRespuesta, false);

      }, (error => {

        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');

      }));
    }

  }

  public getEditarVenta(): void{

    this.ventaService.getVenta(this.ventaId).toPromise().then( (accionRespuesta) => {
        try
        {
          this.respuestaGetVenta = accionRespuesta;

          if ( this.respuestaGetVenta.resultado )
          {
            // tslint:disable-next-line: no-string-literal
            this.ventaDto = this.respuestaGetVenta.data['ventaDto'];
            this.obtenerVentaDesdeVentaDto(this.ventaDto);
            this.titulo = 'Editar venta';
            this.botonTitulo = 'Editar venta';
          }

        }catch (errores){

          console.log('Se ha producido un error al transformar la venta' + errores);
        }
      }, (error) => {
        console.log('Error, no se ha podido recuperar la venta' + error);
      }
    );
  }

  private obtenerVentaDesdeVentaDto(ventaDto: any): void{

    if ( ventaDto != null)
    {
      this.venta.id = ventaDto.id;
      this.venta.codigo = ventaDto.codigo;
      this.venta.fechaCreacion = this.limpiarFecha(ventaDto.fechaCreacion);
      this.venta.fechaInicio = this.limpiarFecha(ventaDto.fechaInicio);
      this.venta.fechaFin = this.limpiarFecha(ventaDto.fechaFin);
      this.venta.descripcion = ventaDto.descripcion;
      this.venta.baseImponibleTotal = ventaDto.baseImponibleTotal;
      this.venta.impuesto = ventaDto.impuesto;
      this.venta.importeTotal = ventaDto.importeTotal;
      // Limpiamos la venta previamente
      this.venta.lineaVenta.pop();
      // Inyectamos el array
      if (ventaDto.lineasVentaDto !== 'undefined')
      {
        // tslint:disable-next-line: prefer-const forin prefer-const
        for (let i in ventaDto.lineasVentaDto)
        {
          this.venta.lineaVenta.push(ventaDto.lineasVentaDto[i]);
        }
      }
    }
  }

  private respuestaCrearEditarVenta(accionRespuesta: AccionRespuesta, esEditarVenta: boolean): void {

    // console.log('Esta registrado' + accionRespuesta.resultado);
    // console.log('Datos que nos devuelve spring: ' + JSON.stringify(accionRespuesta));
    // Si el resultado es true, navegamos hasta la vista
    if (accionRespuesta.resultado && accionRespuesta.id !== null ) {

      this.router.navigate(['ventas', 'venta', accionRespuesta.id]);

      if (esEditarVenta != null && esEditarVenta ){

        swal('Venta editada', 'Se ha editado la venta correctamente', 'success');

       }else{

        swal('Nueva venta', 'Se ha creado la venta correctamente', 'success');

       }

    }else{

      if ( accionRespuesta != null && accionRespuesta.data != null && accionRespuesta.data !=  null )
      {
        this.erroresFormulario = accionRespuesta.data;
      }else
      {
        swal('Error', 'Se ha producido un error al guardar los datos de la venta', 'error');
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

  private rellenarVentaConTablaLineaArticulos(): void{
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
        this.venta.articulosCantidadMap.set(celdaArticuloId, celdaCantidad);
      }
    }
  }

  private convierteMapaEnObjecto(): void{
    // Convertimos el mapa en object
    this.venta.articulosCantidadMap.forEach((value, key) => {
      this.venta.articulosCantidad[key] = value;
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
