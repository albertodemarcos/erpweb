import { Component, OnInit, AfterViewInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { DatePipe } from '@angular/common';
import { CompraService } from 'src/app/services/compras/compra.service';
import { Compra } from 'src/app/model/entitys/compra.model';


declare var jQuery: any;
declare var TableExport: any;

@Component({
  selector: 'app-listado-compras',
  templateUrl: './listado-compras.component.html',
  styleUrls: ['./listado-compras.component.css']
})
export class ListadoComprasComponent implements OnInit, AfterViewInit {

  public tituloListado: string;
  private jqGridId: string;
  private jqGridPagerId: string;
  private jqGridColNames: string[];
  private jqGridColModel: {};
  private jqGridData: Compra[];
  private tableExport: any;
  private botonRetorno: string;

  constructor(private compraService: CompraService, private router: Router, private activatedRoute: ActivatedRoute) {
    this.tituloListado = 'Listado de compras';
    this.botonRetorno = '<button class="btn btn-primary btn-xs" style="margin: 0%; width: 15 px; height: 30px"><i class="fa fa-search-minus" aria-hidden="true"></i></button>';
    this.jqGridId = 'compras-grid';
    this.jqGridPagerId = 'compras-pager';
    this.jqGridColNames = ['ID', 'Ver', 'Código', 'Fecha Compra', 'Base Impl.', 'Importe total'];
    this.jqGridColModel = [
      { name: 'id', index: '', hidden: true},
      { name: '', index: '', width: '60', height: '50', align: 'center', search: false, sortable: false, formatter: () => this.botonRetorno },
      { name: 'codigo', index: '', width: '', align: 'center', search: true, sortable: true },
      { name: 'fechaCompra', index: '', width: '', align: 'center', search: true, sortable: true, formatter: (fechaCompra: any) => this.formatearFecha(fechaCompra) },
      { name: 'baseImponibleTotal', index: '', width: '', align: 'center', search: true, sortable: true, formatter: (baseImponibleTotal: any) => baseImponibleTotal + ' €' },
      { name: 'importeTotal', index: '', width: '', align: 'center', search: true, sortable: true, formatter: (importeTotal: any) => importeTotal + ' €'}
    ];
    this.jqGridData = new Array<Compra>();
  }

  public getListadoCompras(): void{

    console.log('Entramos en el metodo getListadoCompras()');

    this.compraService.getCompras().then( (compras) => {
        try {
          // Introducimos los datos
          compras.forEach(compra => this.jqGridData.push(compra));
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

  private formatearFecha(fechaCompra: any){
    const datePipe: DatePipe = new DatePipe('es-ES');
    if (fechaCompra != null)
    {
      return datePipe.transform(fechaCompra, 'dd/MM/yyyy');
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
        console.log('Se ha pulsado sobre el boton ver para ir a la compra con id: ' + iCol);
        if (iCol === 1 )
        {
          // Obtenemos el valor de la columna oculta
          const idCelValue = jQuery( '#' + this.jqGridId ).jqGrid ('getCell', rowid, 'id');
          console.log('Se ha pulsado sobre el boton ver para ir a la compra con id: ' + idCelValue);
          this.router.navigate(['compras', 'compra', idCelValue]);
        }
      }
    }).jqGrid('navGrid');

    // Filtros
    jQuery('#' + this.jqGridId).jqGrid('filterToolbar', {searchOperators : true});

    // Refrescar
    jQuery('#refresh_' + this.jqGridId).on('click', () => {
      // Limpiamos previamente
      this.jqGridData = new Array<Compra>();
      // Pedimos los datos
      this.getListadoCompras();
    });

    // Exportar a Excel
    jQuery('#exportar').on('click', () => {

      console.log('Se inicia la exportacion a excel del listado de compras');

      // Inicializamos la varaible
      this.tableExport = new TableExport(jQuery('#' + this.jqGridId), {
        header: true,
        footers: true,
        formats: ['xlsx', 'csv', 'txt'],
        filename : 'listado_compras',
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
      const exportData = this.tableExport.getExportData()['compras-grid'].xlsx;

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
    this.getListadoCompras();
  }

}
