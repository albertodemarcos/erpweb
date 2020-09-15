import { Component, OnInit, ViewChild, AfterViewInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
// Contrato
import { ContratoService } from 'src/app/services/ventas/contrato.service';
import { Contrato } from 'src/app/model/entitys/contrato.model';
import { LineaContrato } from 'src/app/model/entitys/linea-contrato.model';
// Articulo
import { ModalArticuloComponent } from 'src/app/components/modales/inventario/modal-articulo/modal-articulo.component';
import { AutocompletarService } from 'src/app/services/autocompletar/autocompletar.service';
// Otros
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import swal from 'sweetalert2';

// jQuery
declare var jQuery: any;


@Component({
  selector: 'app-formulario-contrato',
  templateUrl: './formulario-contrato.component.html',
  styleUrls: ['./formulario-contrato.component.css']
})
export class FormularioContratoComponent implements OnInit, AfterViewInit {

  public contrato: Contrato;
  private contratoId: number;
  private contratoDto: any;
  public tiposImpuesto: string[];
  private respuestaGetContrato: AccionRespuesta;
  public erroresFormulario: Map<string, object>;
  public lineasContratoError: LineaContrato[];
  public mapaIva: Map<string, string>;

  // Modal Articulo
  @ViewChild('modalArticulo') modalArticulo: ModalArticuloComponent;

  constructor(
    private contratoService: ContratoService,
    private autocompletarService: AutocompletarService,
    private router: Router,
    private activateRouter: ActivatedRoute) {

    this.contrato = new Contrato();
    this.contrato.articulosCantidadMap = new Map<number, number>();
    this.contrato.articulosAlmacenMap = new Map<number, number>();
    this.contrato.articulosCantidad = {};
    this.contrato.articulosAlmacen = {};
    this.contrato.lineaContrato = new Array<LineaContrato>();
    this.erroresFormulario = new Map<string, object>();
    this.lineasContratoError = new Array<LineaContrato>();
    this.tiposImpuesto = ['IVA_GENERAL', 'IVA_REDUCIDO', 'IVA_SUPER_REDUCIDO'];

    this.mapaIva = new Map<string, string>();
    this.rellenaMapaIva();

    this.activateRouter.params.subscribe( params => {
      console.log('Entro al constructor' + params);
      // tslint:disable-next-line: no-string-literal
      this.contratoId = params['id'];
      if (this.contratoId != null){
        this.getEditarContrato();
      }
    });
    this.autocompletarService.paramatroExterno = 'tablaArticulos';
    this.modalArticulo = new ModalArticuloComponent(this.autocompletarService);
    /*this.modalArticulo.articuloEvento.subscribe( (articulo: any) => {
      console.log('Articulo dsadadsad: ' + JSON.stringify(articulo));
    });*/
  }

  ngAfterViewInit(): void {
    jQuery.getScript('assets/js/otros/funcionesJS.js').done(() => {
      console.log('Se carga el archivo');
    }).fail(() => {
      console.error('Error, no se ha podido cargar el archivo');
    });
  }

  ngOnInit(): void {
  }

  // Metodos del formulario
  public crearContratoFormulario(): void {

    console.log('Estamos dentro del metodo crearcontratoFormulario()');

    this.rellenarContratoConTablaLineaArticulos();

    /*if (!this.compruebaCantidadArticulosEnStock())
    {
      swal('Error', 'No puedes realizar el contrato. Comprueba el stock de los articulos primero.', 'error');
      return;
    }*/

    // Si tiene id, llamamos a crear, sino a editar
    if (this.contrato != null && this.contrato.id != null && this.contrato.id !== 0) {

      console.log('Vamos a editar el contrato con ID: ' + this.contrato.id);

      this.contratoService.actualizarContrato(this.contrato).subscribe( accionRespuesta => {

        this.respuestaCrearEditarContrato(accionRespuesta, true);

      }, (error => {

        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');

      }));

    } else {

      this.contratoService.crearContrato(this.contrato).subscribe( accionRespuesta => {

        console.log('Vamos a crear el contrato con codigo: ' + this.contrato.codigo);

        this.respuestaCrearEditarContrato(accionRespuesta, false);

      }, (error => {

        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');

      }));
    }

  }

  public getEditarContrato(): void{

    this.contratoService.getContrato(this.contratoId).toPromise().then( (accionRespuesta) => {
        try
        {
          console.log('Recuperamos el contrato');

          this.respuestaGetContrato = accionRespuesta;

          if ( this.respuestaGetContrato.resultado )
          {
            console.log('Respuesta: ' +  JSON.stringify(this.respuestaGetContrato.data) );
            console.log('ES: ' + typeof(this.respuestaGetContrato.data));
            // tslint:disable-next-line: no-string-literal
            this.contratoDto = this.respuestaGetContrato.data['contratoDto'];
            this.obtenerContratoDesdeContratoDto(this.contratoDto);
          }

        }catch (errores){

          console.log('Se ha producido un error al transformar el contrato' + errores);
        }
      }, (error) => {
        console.log('Error, no se ha podido recuperar el contrato' + error);
      }
    );
  }

  private obtenerContratoDesdeContratoDto(contratoDto: any): void{

    if ( contratoDto != null)
    {
      this.contrato.id = contratoDto.id;
      this.contrato.codigo = contratoDto.codigo;
      this.contrato.fechaCreacion = this.limpiarFecha(contratoDto.fechaCreacion);
      this.contrato.fechaInicio = this.limpiarFecha(contratoDto.fechaInicio);
      this.contrato.fechaFin = this.limpiarFecha(contratoDto.fechaFin);
      this.contrato.descripcion = contratoDto.descripcion;
      this.contrato.baseImponibleTotal = contratoDto.baseImponibleTotal;
      this.contrato.impuesto = contratoDto.impuesto;
      this.contrato.importeTotal = contratoDto.importeTotal;
      // Limpiamos el contrato previamente
      this.contrato.lineaContrato.pop();
      // Inyectamos el array
      if (contratoDto.lineasContratoDto !== 'undefined')
      {
        // tslint:disable-next-line: prefer-const forin prefer-const
        for (let i in contratoDto.lineasContratoDto)
        {
          this.contrato.lineaContrato.push(contratoDto.lineasContratoDto[i]);
        }
      }
    }
  }

  private respuestaCrearEditarContrato(accionRespuesta: AccionRespuesta, esEditarContrato: boolean): void {

    console.log('Esta registrado' + accionRespuesta.resultado);
    console.log('Datos que nos devuelve spring: ' + JSON.stringify(accionRespuesta));
    // Si el resultado es true, navegamos hasta la vista
    if (accionRespuesta.resultado && accionRespuesta.id !== null ) {

      this.router.navigate(['contratos', 'contrato', accionRespuesta.id]);

      if (esEditarContrato != null && esEditarContrato ){

        swal('contrato editado', 'Se ha editado el contrato correctamente', 'success');

       }else{

        swal('Nuevo contrato', 'Se ha creado el contrato correctamente', 'success');
       }

    }else{
      // Error
      if ( accionRespuesta != null && accionRespuesta.data != null && accionRespuesta.data !=  null )
      {
        console.log('Empezamos');
        this.erroresFormulario = accionRespuesta.data;
        // tslint:disable-next-line: no-string-literal
        if (this.erroresFormulario['lineasContratoDtoError'] != null)
        {
          // tslint:disable-next-line: no-string-literal
          this.lineasContratoError = this.erroresFormulario['lineasContratoDtoError'];
          console.log('antes de la funcion');
          this.recorrerTablaParaMostrarErrores();
        }
      }else
      {
        swal('Error', 'Se ha producido un error al guardar los datos del contrato', 'error');
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

  /*private compruebaCantidadArticulosEnStock(): boolean{

    if (this.contrato.articulosCantidad == null || this.contrato.id)
    {
      return false;
    }
    this.contratoService.comprobarCantidadArticulos(this.contrato.id, this.contrato.articulosCantidad).then(
      (respuesta) => {
          if (respuesta == null || !respuesta.resultado)
          {
            swal('Error', 'Error, no se puede realizar el contrato, intentalo más tarde', 'error');
            return false;
          }
          return true;
    }, (error) => {
      console.log('Error, el servidor no esta disponible en este momento, intentalo mas tarde');
      return false;
    });
  }*/

  private rellenarContratoConTablaLineaArticulos(): void{
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
      const celdaAlmacenId = jQuery(celdas[8]).text(); // Celda 8 es el almacenId..
      if ( celdaArticuloId != null && celdaArticuloId !== 'undefined' && celdaArticuloId.trim() !== '')
      {
        this.contrato.articulosCantidadMap.set(celdaArticuloId, celdaCantidad);
        this.contrato.articulosAlmacenMap.set(celdaArticuloId, celdaAlmacenId);
      }
    }
  }

  private convierteMapaEnObjecto(): void{
    // Convertimos el mapa articulosCantidad en objecto
    this.contrato.articulosCantidadMap.forEach((value, key) => {
      this.contrato.articulosCantidad[key] = value;
    });
    // Convertimos el mapa articulosAlmacen en objecto
    this.contrato.articulosAlmacenMap.forEach((value, key) => {
      this.contrato.articulosAlmacen[key] = value;
    });
  }

  public modalAnadirArticulo(){
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

  public recorrerTablaParaMostrarErrores()
  {
    console.log('Entramos');
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
      const celdaAlmacenId = jQuery(celdas[8]).text(); // Celda 8 es el almacenId..
      if ( celdaArticuloId != null && celdaArticuloId !== 'undefined' && celdaArticuloId.trim() !== '')
      {
        const keyMap = '' + celdaArticuloId + '-' + celdaAlmacenId;

        if (this.lineasContratoError[keyMap] != null)
        {
          const errorStr = this.lineasContratoError[keyMap];
          jQuery('#linea_art_id_' + celdaArticuloId).addClass('table-danger');
          jQuery('#linea').append(errorStr);
          jQuery('#linea').append('<br/>');
          jQuery('#linea').removeClass('ocultar');
        }
      }
     }
  }

}
