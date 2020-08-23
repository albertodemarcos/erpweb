import { Component, OnInit, Output, EventEmitter } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import { FormularioCompraComponent } from 'src/app/components/formularios/compras/formulario-compra/formulario-compra.component';
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
  public articuloInfo: Articulo;
  public cantidad: number;
  private idTabla: string;
  public tiposImpuesto: string[];
  public mapaIva: Map<string, string>;
  public erroresFormulario: Map<string, object>;

  // Autocompletar
  public keyword: string;
  public dataArticulo: Articulo[];
  public mensajeError: string;
  public estaCargando: boolean;
  private buttonTrash: string;

  @Output() articuloEvento: EventEmitter<Articulo> = new EventEmitter<Articulo>();

  constructor(private autocompletarService: AutocompletarService) {
    this.idTabla = autocompletarService.paramatroExterno;
    this.buttonTrash = '<i class="fa fa-trash" onclick="" aria-hidden="true"></i>';
    this.dataArticulo = new Array<Articulo>();
    this.articuloInfo = new Articulo();
    this.tiposImpuesto = ['IVA_GENERAL', 'IVA_REDUCIDO', 'IVA_SUPER_REDUCIDO'];
    this.mapaIva = new Map<string, string>();
    this.rellenaMapaIva();
    this.erroresFormulario = new Map<string, object>();
  }

  ngOnInit(): void {
  }

  public getArticulos(term: any){
    this.estaCargando = true;
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
            this.mensajeError = 'No existe el almacen';
          }
          this.estaCargando = false;
        } catch (errores){
          this.estaCargando = false;
          console.error('Se ha producido un error al convertir la infomracion del servidor' + errores);
        }

      }, (errores) => {
        this.estaCargando = false;
        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');
      }
    );
  }

  public crearAnadirArticuloFormulario(){
    // Comprobamos si tenemos articulos (Solo ventas)
    if (this.idTabla != null)
    {
      // Comprobamos que no se ha añadido el articulo previamente
      if (!this.existeArticuloEnTabla(this.articuloInfo.id))
      {
        // Introducimos datos
        this.rellenarFilaTabla(this.idTabla);
        // Limpiamos datos
        this.articuloInfo = new Articulo();
        this.limpiarCuadroTextoArticulo();
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

  /*public destruirLineaArticulo(id: string){
    console.log('Enmtramos a destruir');
    if (id != null && id !== 'undefined' && id.trim() !== '')
    {
      console.log('Enmtramos a destruir');
      const lineaArticuloId = 'linea_art_id_' + id;
      $('#' + lineaArticuloId).remove();
    }
    else
    {
      swal('Error', 'Error, no se puede eliiminar la fila, inténtalo mas tarde', 'error');
    }
  }*/

  /* MODAL */

  public mostrarModalCrearArticulo(): void {
    $('#crearModalArticulo').modal('show');
  }

  public ocultarModalCrearArticulo(): void {
    $('#crearModalArticulo').modal('hide');
  }
}
