import { Component, OnInit , AfterViewInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ArticuloService } from 'src/app/services/inventario/articulo.service';
import { Articulo } from 'src/app/model/entitys/articulo.model';

declare var jQuery: any;
declare var TableExport: any;

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
  private tableExport: any;
  private botonRetorno: string;

  constructor(private articuloService: ArticuloService, private router: Router, private activatedRoute: ActivatedRoute) {
    this.tituloListado = 'Listado de Articulos';
    this.botonRetorno = '<button class="btn btn-primary btn-xs" style="margin: 0%; width: 15 px; height: 30px"><i class="fa fa-search-minus" aria-hidden="true"></i></button>';
    this.jqGridId = 'articulos-grid';
    this.jqGridPagerId = 'articulos-pager';
    this.jqGridColNames = ['ID', 'Ver', 'Código', 'Nombre', 'Base Imp.', 'Impuesto', 'Importe total' ];
    this.jqGridColModel = [
      { name: 'id', index: '', hidden: true},
      { name: '', index: '', width: '60', height: '50', align: 'center', search: false, sortable: false, formatter: () => this.botonRetorno },
      { name: 'codigo', index: '', width: '', align: 'center', search: true, sortable: true },
      { name: 'nombre', index: '', width: '', search: true, sortable: true },
      { name: 'baseImponible', index: '', width: '', align: 'center', search: true, sortable: true, formatter: (baseImponible: any) => baseImponible + ' €' },
      { name: 'impuesto', index: '', width: '', align: 'center', search: true, sortable: true, formatter: (impuesto: any) => this.formatearImpuesto(impuesto) },
      { name: 'importeTotal', index: '', width: '', align: 'center', search: true, sortable: true, formatter: (importeTotal: any) => importeTotal + ' €'  }
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

  formatearImpuesto(impuesto: string): string {
    if (impuesto != null && impuesto !== 'undefined' && impuesto.trim() !== '')
    {
      switch (impuesto)
      {
        case 'IVA_GENERAL':
          return 'IVA GENERAL (21%)';

        case 'IVA_REDUCIDO':
          return 'IVA REDUCIDO (10%)';

        case 'IVA_SUPER_REDUCIDO':
          return 'IVA SUPER REDUCIDO (4%)';

        default:
          return '';
      }
    }
    return '';
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
      autowidth: true,
      shrinkToFit: true,
      guiStyle: 'bootstrap4',
      iconSet: 'fontAwesome',
      loadonce: false,
        searching: {
        multipleSearch: true,
        loadDefaults: false,
        sopt: ['eq', 'ne', 'cn', 'bw', 'bn', 'ge', 'le', 'lt', 'gt'],
        showQuery: false
      },
      navOptions: { add: false, edit: false, search: false, del: false, refresh: true, refreshstate: 'current' },
      onCellSelect: (rowid: any, iCol: any, cellcontent: any, e: any) => {
        // Si se pulsa sobre la columna 1, pulsan sobre el boton
        console.log('Se ha pulsado sobre el boton ver para ir al articulo con id: ' + iCol);
        if (iCol === 1 )
        {
          // Obtenemos el valor de la columna oculta
          const idCelValue = jQuery( '#' + this.jqGridId ).jqGrid ('getCell', rowid, 'id');
          console.log('Se ha pulsado sobre el boton ver para ir al articulo con id: ' + idCelValue);
          this.router.navigate(['catalago', 'articulo', idCelValue]);
        }
      }
    }).jqGrid('navGrid');

    // Filtros
    jQuery('#' + this.jqGridId).jqGrid('filterToolbar', {searchOperators : true});

    // Refrescar
    jQuery('#refresh_' + this.jqGridId).on('click', () => {
      // Limpiamos previamente
      this.jqGridData = new Array<Articulo>();
      // Pedimos los datos
      this.getListadoArticulos();
    });

    // Exportar a Excel
    jQuery('#exportar').on('click', () => {

      console.log('Se inicia la exportacion a excel del listado de articulos');

      // Inicializamos la varaible
      this.tableExport = new TableExport(jQuery('#' + this.jqGridId), {
        header: true,
        footers: true,
        formats: ['xlsx', 'csv', 'txt'],
        filename : 'listado_articulos',
        bootstrap: true,
        exportButtons: false,
        position: 'bottom',
        ignoreRows: null,
        ignoreCols: [0, 2],
        trimWhitespace: true,
        RTL: false,
        sheetname: 'id'
      });

      // Obtenemos los datos
      const exportData = this.tableExport.getExportData()['articulos-grid'].xlsx;

      // Exportamos los datos
      this.tableExport.export2file(
        exportData.data,
        exportData.mimeType,
        exportData.filename,
        exportData.fileExtension
      );
    });

  }

  ngOnInit(): void {
    this.getListadoArticulos();
  }

}
