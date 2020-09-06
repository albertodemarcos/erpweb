import { Component, OnInit, AfterViewInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { DatePipe } from '@angular/common';
import { FacturaService } from 'src/app/services/ventas/factura.service';
import { Factura } from 'src/app/model/entitys/factura.model';

declare var jQuery: any;
declare var TableExport: any;

@Component({
  selector: 'app-listado-facturas',
  templateUrl: './listado-facturas.component.html',
  styleUrls: ['./listado-facturas.component.css']
})
export class ListadoFacturasComponent implements OnInit, AfterViewInit {

  public tituloListado: string;
  private jqGridId: string;
  private jqGridPagerId: string;
  private jqGridColNames: string[];
  private jqGridColModel: {};
  private jqGridData: Factura[];
  private tableExport: any;

  constructor(private facturaService: FacturaService, private router: Router, private activatedRoute: ActivatedRoute) {
    this.tituloListado = 'Listado de Facturas';
    this.jqGridId = 'facturas-grid';
    this.jqGridPagerId = 'facturas-pager';
    this.jqGridColNames = ['ID', 'Ver', 'Código', 'F. Inicio', 'Base Impl.', 'Importe Total' ];
    this.jqGridColModel = [
      { name: 'id', index: '', hidden: true},
      { name: '', index: '', width: '60', height: '50', align: 'center', search: false, sortable: false, formatter:
        () => {
          return '<button class="btn btn-primary btn-xs" style="margin: 0%; width: 15 px; height: 30px">' +
          '<i class="fa fa-search-minus" aria-hidden="true"></i></button>';
        }
      },
      { name: 'codigo', index: '', width: '', search: true, sortable: true },
      { name: 'fechaInicio', index: '', width: '', align: 'center', search: true, sortable: true, formatter:
        (fechaInicio: any) => {
          const datePipe: DatePipe = new DatePipe('es-ES');
          return datePipe.transform(fechaInicio, 'dd/MM/yyyy');
        }
      },
      { name: 'baseImponible', index: '', width: '', search: true, sortable: true },
      { name: 'importeTotal', index: '', width: '', search: true, sortable: true }
    ];
    this.jqGridData = new Array<Factura>();
  }

  getListadoFacturas(): void{

    console.log('Entramos en el metodo getListadoFacturas()');

    this.facturaService.getFacturas().then( (facturas) => {
        try {
          // Introducimos los datos
          facturas.forEach(factura => this.jqGridData.push(factura));
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
        console.log('Se ha pulsado sobre el boton ver para ir a la factura con id: ' + iCol);
        if (iCol === 1 )
        {
          // Obtenemos el valor de la columna oculta
          const idCelValue = jQuery( '#' + this.jqGridId ).jqGrid ('getCell', rowid, 'id');
          console.log('Se ha pulsado sobre el boton ver para ir a la factura con id: ' + idCelValue);
          this.router.navigate(['facturas', 'factura', idCelValue]);
        }
      }
    }).jqGrid('navGrid');

    // Filtros
    jQuery('#' + this.jqGridId).jqGrid('filterToolbar', {searchOperators : true});

    // Refrescar
    jQuery('#refresh_' + this.jqGridId).on('click', () => {
      // Limpiamos previamente
      this.jqGridData = new Array<Factura>();
      // Pedimos los datos
      this.getListadoFacturas();
    });

    // Exportar a Excel
    jQuery('#exportar').on('click', () => {

      console.log('Se inicia la exportacion a excel del listado de facturas');

      // Inicializamos la varaible
      this.tableExport = new TableExport(jQuery('#' + this.jqGridId), {
        header: true,
        footers: true,
        formats: ['xlsx', 'csv', 'txt'],
        filename : 'listado_facturas',
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
      const exportData = this.tableExport.getExportData()['facturas-grid'].xlsx;

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
    this.getListadoFacturas();
  }


}
