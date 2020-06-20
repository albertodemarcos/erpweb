import { Component, OnInit , AfterViewInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ArticuloService } from 'src/app/services/inventario/articulo.service';
import { Articulo } from 'src/app/model/entitys/articulo.model';

declare var jQuery: any;

@Component({
  selector: 'app-listado-articulos',
  templateUrl: './listado-articulos.component.html',
  styleUrls: ['./listado-articulos.component.css']
})
export class ListadoArticulosComponent implements OnInit, AfterViewInit {

  public tituloListado: string;
  private jqGridId: string;
  private jqGridPagerId: string;
  private jqGridColNames: string[];
  private jqGridColModel: {};
  private jqGridData: Articulo[];

  constructor(private articuloService: ArticuloService, private router: Router, private activatedRoute: ActivatedRoute) {
    this.tituloListado = 'Listado de Articulos';
    this.jqGridId = 'articulos-grid';
    this.jqGridPagerId = 'articulos-pager';
    this.jqGridColNames = ['', 'Código', 'Nombre', 'Base Imp.', 'Impuesto', 'Importe total' ];
    this.jqGridColModel = [
      { name: 'id', index: '', width: '40', search: false, sortable: false },
      { name: 'codigo', index: '', width: '', search: true, sortable: true },
      { name: 'nombre', index: '', width: '', search: true, sortable: true },
      { name: 'baseImponible', index: '', width: '', search: true, sortable: true },
      { name: 'impuesto', index: '', width: '', search: true, sortable: true },
      { name: 'importeTotal', index: '', width: '', search: true, sortable: true }
    ];
    this.jqGridData = new Array<Articulo>();
  }

  getListadoArticulos(): void{

    console.log('Entramos en el metodo getListadoArticulos()');

    this.articuloService.getArticulos().then( (articulos) => {
        try {
          // Introducimos los datos
          articulos.forEach(articulo => this.jqGridData.push(articulo));
          // Reload JqGrid
          jQuery('#' + this.jqGridId).jqGrid('setGridParam', {data: this.jqGridData}).trigger('reloadGrid');
        } catch (errores){
          console.error('Se ha producido un error al convertir la infomracion del servidor' + errores);
        }
      }, (error) => {
        console.log('Error, no se ha obtenido la informacion');
      }
    );
  }

  ngAfterViewInit(): void {

    // JqGrid
    ( jQuery ('#' + this.jqGridId ) ).jqGrid({
      colNames: this.jqGridColNames,
      colModel: this.jqGridColModel,
      pager: this.jqGridPagerId,
      caption: '',
      rowNum: 10,
      rowList: [10, 20],
      viewrecords: true,
      gridview: true,
      autowidth: true
    });

    // Filtros
    jQuery('#' + this.jqGridId).jqGrid('filterToolbar', {searchOperators : true});

    jQuery('#exportar').on('click', () => {

      console.log('Se inicia la exportacion a excel del listado de articulos');

      jQuery('#' + this.jqGridId).tableExport({ type: 'excel', fileName: 'listado-articulos' , escape: 'false'} );

    });

  }

  ngOnInit(): void {
    this.getListadoArticulos();
  }

}
