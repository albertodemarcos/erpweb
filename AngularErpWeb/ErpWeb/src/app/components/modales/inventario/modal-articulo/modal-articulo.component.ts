import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import { Almacen } from 'src/app/model/entitys/almacen.model';
import { Articulo } from 'src/app/model/entitys/articulo.model';
// Autocompletar
import { AutocompletarService } from 'src/app/services/autocompletar/autocompletar.service';

// Swat alet
import swal from 'sweetalert2';


declare var $: any;

@Component({
  selector: 'app-modal-articulo',
  templateUrl: './modal-articulo.component.html',
  styleUrls: ['./modal-articulo.component.css']
})
export class ModalArticuloComponent implements OnInit {

  public articulo: Articulo;
  public almacen: Almacen;
  public almacenInfo: Almacen;
  public articuloInfo: Articulo;
  public cantidad: number;
  private idTabla: string;
  public tiposImpuesto: string[];
  public mapaIva: Map<string, string>;
  public erroresFormulario: Map<string, object>;

  // Autocompletar
  public dataAlmacen: Almacen[];
  public dataArticulo: Articulo[];
  public mensajeError: string;
  private errorModal: boolean;
  public estaCargandoAlmacen: boolean;
  public estaCargandoArticulo: boolean;

  @Output() articuloEvento: EventEmitter<Articulo> = new EventEmitter<Articulo>();

  constructor(private autocompletarService: AutocompletarService) {

    console.log('Estoy en el autocompletarService');

    this.idTabla = autocompletarService.paramatroExterno;
    this.dataAlmacen = new Array<Almacen>();
    this.dataArticulo = new Array<Articulo>();
    this.articuloInfo = new Articulo();
    this.almacenInfo = new Almacen();
    this.almacen = null;
    this.articulo = null;
    this.tiposImpuesto = ['IVA_GENERAL', 'IVA_REDUCIDO', 'IVA_SUPER_REDUCIDO'];
    this.mapaIva = new Map<string, string>();
    this.rellenaMapaIva();
    this.erroresFormulario = new Map<string, object>();
    this.errorModal = false;
  }

  ngOnInit(): void {

    $('#almacenId').autocomplete({
      minLength: 2,
      source: (request: any, response: any) => {
        this.getAlmacenesAutocompletar(request, response);
      },
      focus: ( event: any, ui: any ) => {
        console.log('Estoy en el foco');
        $('#almacenId').val( ui.item.nombre );
        $('#almacenId').attr('data-almacen-id', ui.item.id);
        $('#almacenSpanId').attr('data-almacen-id', ui.item.id);
        return false;
      },
      select: ( event: any, ui: any ) => {
        this.rellenarAlmacenDeAutocompletar(ui.item);
        console.log('Estoy en el selector');
        $('#almacenId').val( ui.item.nombre );
        $('#almacenId').attr('data-almacen-id', ui.item.id);
        $('#almacenSpanId').attr('data-almacen-id', ui.item.id);
        return false;
      }
    }).autocomplete('instance')._renderItem = ( ul: any, item: any ) => {
      const codigo = item.codigo;
      if (item != null && item !== 'undefined' && codigo != null && codigo !== 'undefined' && codigo.trim() !== '' )
      {
        return $('<li>').append('<a>' + codigo + '-' + item.nombre + '</a>').appendTo(ul);
      }
      // Si no tiene codigo
      return $('<li>').append('<a>' + item.nombre + '</a>').appendTo(ul);
   };

    $('#articuloId').autocomplete({
      minLength: 2,
      source: (request: any, response: any) => {
        this.getArticulosAutocompletar(request, response);
      },
      focus: ( event: any, ui: any ) => {
        $('#articuloId').val( ui.item.nombre );
        $('#articuloId').attr('data-articulo-id', ui.item.id);
        $('#articuloSpanId').attr('data-articulo-id', ui.item.id);
        return false;
      },
      select: ( event: any, ui: any ) => {
        this.rellenarArticuloDeAutocompletar(ui.item);
        $('#articuloId').val( ui.item.nombre );
        $('#articuloId').attr('data-articulo-id', ui.item.id);
        $('#articuloSpanId').attr('data-articulo-id', ui.item.id);
        return false;
      }
    }).autocomplete('instance')._renderItem = ( ul: any, item: any ) => {
      const codigo = item.codigo;
      if (item != null && item !== 'undefined' && codigo != null && codigo !== 'undefined' && codigo.trim() !== '' )
      {
        return $('<li>').append('<a>' + codigo + '-' + item.nombre + '</a>').appendTo(ul);
      }
      // Si no tiene codigo
      return $('<li>').append('<a>' + item.nombre + '</a>').appendTo(ul);
    };

  }

  private getAlmacenesAutocompletar(request: any, response: any){
    this.estaCargandoAlmacen = true;
     // Limpiamos el array previamente
    this.dataAlmacen = new Array<Almacen>();
    this.autocompletarService.getAlmacenAutocompletar(request.term).then(
      (almacenes) => {
        try {
          // Introducimos los datos
          if ( almacenes !== null )
          {
            almacenes.forEach(almacen => {
              this.dataAlmacen.push(almacen);
            });
            return response(this.dataAlmacen);
          }
          else
          {
            this.mensajeError = 'No existe el almacen';
          }
          this.estaCargandoAlmacen = false;
          return response;

        } catch (errores){
          console.error('Se ha producido un error al convertir la infomracion del servidor' + errores);
          this.estaCargandoAlmacen = false;
          return response;
        }

      }, (errores) => {
        this.estaCargandoAlmacen = false;
        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');
        return response;
      }
    );
  }

  private getArticulosAutocompletar(request: any, response: any){
    this.estaCargandoArticulo = true;
    // Limpiamos el array previamente
    this.dataArticulo = new Array<Articulo>();
    this.autocompletarService.getArticuloAutcompletar(request.term, true).then(
      (articulos) => {
        try {
          // Introducimos los datos
          if ( articulos !== null )
          {
            articulos.forEach(articulo => {
              this.dataArticulo.push(articulo);
            });
            return response(this.dataArticulo);
          }
          else
          {
            this.mensajeError = 'No existe el articulo';
          }
          this.estaCargandoArticulo = false;
          return response;

        } catch (errores){
          this.estaCargandoArticulo = false;
          console.error('Se ha producido un error al convertir la infomracion del servidor' + errores);
          return response;
        }

      }, (errores) => {
        this.estaCargandoArticulo = false;
        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');
        return response;
      }
    );
  }

  public crearAnadirArticuloFormulario(){
    // Comprobamos si tenemos articulos
    if (this.idTabla != null)
    {
      // Comprobamos que no se ha añadido el articulo previamente
      if (!this.existeArticuloEnTabla(this.articuloInfo.id))
      {
        // Introducimos datos
        this.rellenarFilaTabla(this.idTabla);
        console.log('Entramos al');
        if (this.errorModal)
        {
          this.errorModal = false;
          return;
        }
        // Limpiamos la modal
        this.limpiarCuadrosTextoArticuloAlmacen();
        // Cerramos modal
        this.ocultarModalCrearArticulo();
      }
      else
      {
        swal('Error', 'No puedes añadir el mismo articulo!', 'error');
      }
      return;
    }
    console.error('Error, no existe la tabla');
  }

  private limpiarCuadrosTextoArticuloAlmacen(): void{
    // Limpiamos datos almacen
    this.almacenInfo = new Almacen();
    // Limpiamos datos articulo
    this.articuloInfo = new Articulo();
    // Otros datos
    this.dataArticulo = new Array<Articulo>();
    this.dataAlmacen = new Array<Almacen>();
    // Limpiamos la cantidad
    this.cantidad = null;
    // Limpiamos autocompletar
    $('#almacenId').val('');
    $('#articuloId').val('');
  }

  /* METODOS AUXLIARES */

  private rellenaMapaIva(): void{
    this.mapaIva.set('IVA_GENERAL', 'GENERAL (21%)');
    this.mapaIva.set('IVA_REDUCIDO', 'REDUCIDO (10%)');
    this.mapaIva.set('IVA_SUPER_REDUCIDO', 'SUPER REDUCIDO (4%)');
  }

  private rellenarFilaTabla(idTabla: string): void {
    const id = '' + this.articuloInfo.id;
    const almcenId = '' + this.almacenInfo.id;
    this.comprobarAutocompletar();
    if ( !this.errorModal )
    {
      const funcion = 'onclick="destruirLineaArticulo(' + id + ')"';
      // Agregamos una fila nueva
      const filaTabla =
      '<tr id="linea_art_id_' + id + '" data-art-id="' + id + '">' +
        '<td class="ocultar">' + id + '</td>' +
        '<td>' + this.articuloInfo.codigo + '</td>' +
        '<td>' + this.articuloInfo.nombre + '</td>' +
        '<td>' + this.articuloInfo.baseImponible + '€' + '</td>' +
        '<td>' + this.mapaIva.get(this.articuloInfo.impuesto) + '</td>' +
        '<td>' + this.articuloInfo.importeTotal + '€' + '</td>' +
        '<td>' + this.cantidad + '</td>' +
        '<td>' + (this.articuloInfo.importeTotal * this.cantidad) + '€' + '</td>' +
        '<td class="ocultar">' + (almcenId) + '</td>' +
        '<td class="text-center">' +
          '<button type="button" class="btn btn-danger" ' + funcion + ' >' +
            '<i class="fa fa-trash" aria-hidden="true"></i>' +
          '</button>' +
        '</td>' +
      '</tr>';
      $('#' + idTabla + ' tbody').append(filaTabla);
    }
  }

  private existeArticuloEnTabla(id: number): boolean{

    const filas = $('#' + this.idTabla).find('tr');
    // tslint:disable-next-line: prefer-for-of
    for (let i = 0; i < filas.length; i++)
    {
      // Recuperamos las celdas
      const celdas = $(filas[i]).find('td');
      // Obtenemos las celdas de articulo y cantidad
      const celdaArticuloId = $(celdas[0]).text(); // Celda 0 es articuloId..
      if ( celdaArticuloId === ('' + id) )
      {
        return true; // Si existe finalizamos, sino seguimos
      }
    }
    return false;
  }

  private comprobarAutocompletar() {

    const articuloId = '' + this.articuloInfo.id;
    const almcenId = '' + this.almacenInfo.id;
    const cantidad = '' + this.cantidad;

    if (almcenId == null || almcenId === 'undefined' || almcenId === '')
    {
      swal('Error', 'Debes seleccionar un almacen', 'error');
      this.errorModal = true;
      return;
    }

    if ( articuloId == null || articuloId === 'undefined' || articuloId === '')
    {
      swal('Error', 'Debes seleccionar un articulo', 'error');
      this.errorModal = true;
      return;
    }

    if ( cantidad == null || cantidad === 'undefined' || cantidad === '' )
    {
      swal('Error', 'Debes introducir la cantidad', 'error');
      this.errorModal = true;
      return;
    }
  }

  private rellenarAlmacenDeAutocompletar(almacenAuto: any){
    this.almacenInfo.id = almacenAuto.id;
    this.almacenInfo.codigo = almacenAuto.codigo;
    this.almacenInfo.nombre = almacenAuto.nombre;
  }

  private rellenarArticuloDeAutocompletar(articuloAuto: any){
    this.articuloInfo.id = articuloAuto.id;
    this.articuloInfo.codigo = articuloAuto.codigo;
    this.articuloInfo.nombre = articuloAuto.nombre;
    this.articuloInfo.baseImponible = articuloAuto.baseImponible;
    this.articuloInfo.impuesto = articuloAuto.impuesto;
    this.articuloInfo.importeTotal = articuloAuto.importeTotal;
  }

  /* MODAL */

  public mostrarModalCrearArticulo(): void {
    $('#crearModalArticulo').modal('show');
  }

  public ocultarModalCrearArticulo(): void {
    $('#crearModalArticulo').modal('hide');
  }
}
