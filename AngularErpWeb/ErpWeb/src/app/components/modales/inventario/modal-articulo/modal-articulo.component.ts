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
  public keyword: string;
  public dataAlmacen: Almacen[];
  public dataArticulo: Articulo[];
  public mensajeError: string;
  public estaCargandoAlmacen: boolean;
  public estaCargandoArticulo: boolean;

  @Output() articuloEvento: EventEmitter<Articulo> = new EventEmitter<Articulo>();

  constructor(private autocompletarService: AutocompletarService) {
    this.idTabla = autocompletarService.paramatroExterno;
    this.dataAlmacen = new Array<Almacen>();
    this.dataArticulo = new Array<Articulo>();
    this.articuloInfo = new Articulo();
    this.tiposImpuesto = ['IVA_GENERAL', 'IVA_REDUCIDO', 'IVA_SUPER_REDUCIDO'];
    this.mapaIva = new Map<string, string>();
    this.rellenaMapaIva();
    this.erroresFormulario = new Map<string, object>();
  }

  ngOnInit(): void {
  }

  public getAlmacenes(term: any){
    this.estaCargandoAlmacen = true;
     // Limpiamos el array previamente
    this.dataAlmacen = new Array<Almacen>();
    this.autocompletarService.getAlmacenAutocompletar(term).then(
      (almacenes) => {
        try {
          // Introducimos los datos
          if ( almacenes !== null )
          {
            this.keyword = 'nombre';
            almacenes.forEach(almacen => {
              this.dataAlmacen.push(almacen);
            });
          }
          else
          {
            this.mensajeError = 'No existe el almacen';
          }
          this.estaCargandoAlmacen = false;
        } catch (errores){
          console.error('Se ha producido un error al convertir la infomracion del servidor' + errores);
          this.estaCargandoAlmacen = false;
        }

      }, (errores) => {
        this.estaCargandoAlmacen = false;
        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');
      }
    );
  }

  public getArticulos(term: any){
    this.estaCargandoArticulo = true;
    // Limpiamos el array previamente
    this.dataArticulo = new Array<Articulo>();
    this.autocompletarService.getArticuloAutcompletar(term, true).then(
      (articulos) => {
        try {
          // Introducimos los datos
          if ( articulos !== null )
          {
            this.keyword = 'nombre';
            articulos.forEach(articulo => {
              this.dataArticulo.push(articulo);
            });
          }
          else
          {
            this.mensajeError = 'No existe el articulo';
          }
          this.estaCargandoArticulo = false;
        } catch (errores){
          this.estaCargandoArticulo = false;
          console.error('Se ha producido un error al convertir la infomracion del servidor' + errores);
        }

      }, (errores) => {
        this.estaCargandoArticulo = false;
        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');
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
        console.log('Entramos a añadir articulo');
        // Introducimos datos
        this.rellenarFilaTabla(this.idTabla);
        // Limpiamos datos almacen
        this.almacenInfo = new Almacen();
        // Limpiamos datos articulo
        this.articuloInfo = new Articulo();
        this.limpiarCuadroTextoArticuloAlmacen();
        // this.articulo = null;
        this.cantidad = null;
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

  public itemAlmacenSeleccionado(item: any) {
    // do something with selected item
    console.log('Tenemos: ' + item);
    this.almacenInfo = item;
  }

  public itemSeleccionado(item: any) {
    // do something with selected item
    console.log('Tenemos: ' + item);
    this.articuloInfo = item;
  }

  public cambiosAlBuscar(val: string) {
    // fetch remote data from here
    // And reassign the 'data' which is binded to 'data' property.
    console.log('Entramos en cambiosAlBuscar()');
  }

  public cuadroSeleccionado(e: any) {
    // do something when input is focused
    console.log('Entramos en cuadroSeleccionado()');
  }

  public limpiarCuadroTextoArticulo(){
    console.log('Limpiamos ');
    this.dataArticulo = new Array<Articulo>();
  }

  public limpiarCuadroTextoAlmacen(){
    console.log('Limpiamos ');
    this.dataAlmacen = new Array<Almacen>();
  }

  public limpiarCuadroTextoArticuloAlmacen(){
    console.log('Limpiamos ');
    this.dataArticulo = new Array<Articulo>();
    this.dataAlmacen = new Array<Almacen>();
  }

  /* METODOS AUXLIARES */

  private rellenaMapaIva(): void{
    this.mapaIva.set('IVA_GENERAL', 'GENERAL (21%)');
    this.mapaIva.set('IVA_REDUCIDO', 'REDUCIDO (10%)');
    this.mapaIva.set('IVA_SUPER_REDUCIDO', 'SUPER REDUCIDO (4%)');
  }

  private rellenarFilaTabla(idTabla: string): void {
    const id = '' + this.articuloInfo.id;
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
      '<td class="ocultar">' + (this.almacenInfo.id) + '</td>' +
      '<td class="text-center">' +
        '<button type="button" class="btn btn-danger" ' + funcion + ' >' +
          '<i class="fa fa-trash" aria-hidden="true"></i>' +
        '</button>' +
      '</td>' +
    '</tr>';
    $('#' + idTabla + ' tbody').append(filaTabla);
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

  /* MODAL */

  public mostrarModalCrearArticulo(): void {
    $('#crearModalArticulo').modal('show');
  }

  public ocultarModalCrearArticulo(): void {
    $('#crearModalArticulo').modal('hide');
  }
}
