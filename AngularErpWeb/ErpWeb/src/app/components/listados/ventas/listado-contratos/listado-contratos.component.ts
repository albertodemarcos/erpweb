import { Component, OnInit, AfterViewInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { DatePipe } from '@angular/common';
import { ContratoService } from 'src/app/services/ventas/contrato.service';
import { Contrato } from 'src/app/model/entitys/contrato.model';

declare var jQuery: any;
declare var TableExport: any;

@Component({
  selector: 'app-listado-contratos',
  templateUrl: './listado-contratos.component.html',
  styleUrls: ['./listado-contratos.component.css']
})
export class ListadoContratosComponent implements OnInit, AfterViewInit {

  public tituloListado: string;
  private jqGridId: string;
  private jqGridPagerId: string;
  private jqGridColNames: string[];
  private jqGridColModel: {};
  private jqGridData: Contrato[];
  private tableExport: any;
  private botonRetorno: string;

  constructor(private contratoService: ContratoService, private router: Router, private activatedRoute: ActivatedRoute) {
    this.tituloListado = 'Listado de Contratos';
    this.botonRetorno = '<button class="btn btn-primary btn-xs" style="margin: 0%; width: 15 px; height: 30px"><i class="fa fa-search-minus" aria-hidden="true"></i></button>';
    this.jqGridId = 'contratos-grid';
    this.jqGridPagerId = 'contratos-pager';
    this.jqGridColNames = ['ID', 'Ver', 'Código', 'F. Inicio', 'F. Fín', 'Base Impl.', 'Importe Total' ];
    this.jqGridColModel = [
      { name: 'id', index: '', hidden: true},
      { name: '', index: '', width: '60', height: '50', align: 'center', search: false, sortable: false, formatter: () => this.botonRetorno },
      { name: 'codigo', index: '', width: '', align: 'center', search: true, sortable: true },
      { name: 'fechaInicio', index: '', width: '', align: 'center', search: true, sortable: true, formatter: (fechaInicio: any) => this.formatearFecha(fechaInicio) },
      { name: 'fechaFin', index: '', width: '', align: 'center', search: true, sortable: true, formatter: (fechaFin: any) => this.formatearFecha(fechaFin) },
      { name: 'baseImponibleTotal', index: '', width: '', align: 'center', search: true, sortable: true, formatter: (baseImponibleTotal: any) => baseImponibleTotal + ' €' },
      { name: 'importeTotal', index: '', width: '', align: 'center', search: true, sortable: true, formatter: (importeTotal: any) => importeTotal + ' €' }
    ];
    this.jqGridData = new Array<Contrato>();
  }

  getListadoContratos(): void{

    console.log('Entramos en el metodo getListadoContratos()');

    this.contratoService.getContratos().then( (contratos) => {
        try {
          // Introducimos los datos
          contratos.forEach(contrato => this.jqGridData.push(contrato));
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

  private formatearFecha(fecha: any){
    const datePipe: DatePipe = new DatePipe('es-ES');
    if (fecha != null)
    {
      return datePipe.transform(fecha, 'dd/MM/yyyy');
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
        console.log('Se ha pulsado sobre el boton ver para ir al contrato con id: ' + iCol);
        if (iCol === 1 )
        {
          // Obtenemos el valor de la columna oculta
          const idCelValue = jQuery( '#' + this.jqGridId ).jqGrid ('getCell', rowid, 'id');
          console.log('Se ha pulsado sobre el boton ver para ir al contrato con id: ' + idCelValue);
          this.router.navigate(['contratos', 'contrato', idCelValue]);
        }
      }
    }).jqGrid('navGrid');

    // Filtros
    jQuery('#' + this.jqGridId).jqGrid('filterToolbar', {searchOperators : true});

    // Refrescar
    jQuery('#refresh_' + this.jqGridId).on('click', () => {
      // Limpiamos previamente
      this.jqGridData = new Array<Contrato>();
      // Pedimos los datos
      this.getListadoContratos();
    });

    // Exportar a Excel
    jQuery('#exportar').on('click', () => {

      console.log('Se inicia la exportacion a excel del listado de contratos');

      // Inicializamos la varaible
      this.tableExport = new TableExport(jQuery('#' + this.jqGridId), {
        header: true,
        footers: true,
        formats: ['xlsx', 'csv', 'txt'],
        filename : 'listado_contratos',
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
      const exportData = this.tableExport.getExportData()['contratos-grid'].xlsx;

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
    this.getListadoContratos();
  }

}
