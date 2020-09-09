import { Component, OnInit, AfterViewInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { StockService } from 'src/app/services/inventario/stock.service';
import { Stock } from 'src/app/model/entitys/stock.model';

declare var jQuery: any;
declare var TableExport: any;


@Component({
  selector: 'app-listado-stock',
  templateUrl: './listado-stock.component.html',
  styleUrls: ['./listado-stock.component.css']
})
export class ListadoStockComponent implements OnInit, AfterViewInit {

  public tituloListado: string;
  private jqGridId: string;
  private jqGridPagerId: string;
  private jqGridColNames: string[];
  private jqGridColModel: {};
  private jqGridData: Stock[];
  private tableExport: any;
  private botonRetorno: string;

  constructor(private stockService: StockService, private router: Router, private activatedRoute: ActivatedRoute) {
    this.tituloListado = 'Listado de stock de almacenes';
    this.botonRetorno = '<button class="btn btn-primary btn-xs" style="margin: 0%; width: 15 px; height: 30px"><i class="fa fa-search-minus" aria-hidden="true"></i></button>';
    this.jqGridId = 'stock-grid';
    this.jqGridPagerId = 'stock-pager';
    this.jqGridColNames = ['ID', 'Ver', 'Código', 'Articulo', 'Cantidad', 'Almacen' ];
    this.jqGridColModel = [
      { name: 'id', index: '', hidden: true},
      { name: '', index: '', width: '60', height: '50', align: 'center', search: false, sortable: false, formatter: () => this.botonRetorno },
      { name: 'codigo', index: '', width: '', search: true, sortable: true },
      { name: 'articuloDto.nombre', index: '', width: '', search: true, sortable: true },
      { name: 'cantidad', index: '', align: 'center', width: '', search: true, sortable: true, formatter: this.cantidadFomatter  },
      { name: 'almacenDto.nombre', index: '', width: '', search: true, sortable: true }
    ];
    this.jqGridData = new Array<Stock>();
  }

  private cantidadFomatter(cellvalue: any, options: any, rowObject: any)
  {
    if (cellvalue != null && cellvalue !== 'undefined' && cellvalue.trim !== '')
    {
      return cellvalue + ' uds';
    }
    return '';
  }

  public getListadoStock(): void{

    console.log('Entramos en el metodo getListadoStock()');

    this.stockService.getStockAlmacenes().then( (stocks) => {
        try {
          // Introducimos los datos
          stocks.forEach(stock => this.jqGridData.push(stock));
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
        console.log('Se ha pulsado sobre el boton ver para ir al almacen con id: ' + iCol);
        if (iCol === 1 )
        {
          // Obtenemos el valor de la columna oculta
          const idCelValue = jQuery( '#' + this.jqGridId ).jqGrid ('getCell', rowid, 'id');
          console.log('Se ha pulsado sobre el boton ver para ir al almacen con id: ' + idCelValue);
          this.router.navigate(['stock', 'almacen', idCelValue]);
        }
      }
    }).jqGrid('navGrid');

    // Filtros
    jQuery('#' + this.jqGridId).jqGrid('filterToolbar', {searchOperators : true});

    // Refrescar
    jQuery('#refresh_' + this.jqGridId).on('click', () => {
      // Limpiamos previamente
      this.jqGridData = new Array<Stock>();
      // Pedimos los datos
      this.getListadoStock();
    });

    // Exportar a Excel
    jQuery('#exportar').on('click', () => {

      console.log('Se inicia la exportacion a excel del listado de almacenes');

      // Inicializamos la varaible
      this.tableExport = new TableExport(jQuery('#' + this.jqGridId), {
        header: true,
        footers: true,
        formats: ['xlsx', 'csv', 'txt'],
        filename : 'listado_stock',
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
      const exportData = this.tableExport.getExportData()['stock-grid'].xlsx;

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
    this.getListadoStock();
  }

}
