import { Component, OnInit , AfterViewInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { ClienteService } from 'src/app/services/crm/cliente.service';
import { Cliente } from 'src/app/model/entitys/cliente.model';

declare var jQuery: any;
declare var TableExport: any;

@Component({
  selector: 'app-listado-clientes',
  templateUrl: './listado-clientes.component.html',
  styleUrls: ['./listado-clientes.component.css']
})
export class ListadoClientesComponent implements OnInit, AfterViewInit {

  public tituloListado: string;
  private jqGridId: string;
  private jqGridPagerId: string;
  private jqGridColNames: string[];
  private jqGridColModel: {};
  private jqGridData: Cliente[];
  private tableExport: any;
  private botonRetorno: string;

  constructor(private clienteService: ClienteService, private router: Router, private activatedRoute: ActivatedRoute) {
    this.tituloListado = 'Listado de clientes';
    this.botonRetorno = '<button class="btn btn-primary btn-xs" style="margin: 0%; width: 15 px; height: 30px"><i class="fa fa-search-minus" aria-hidden="true"></i></button>';
    this.jqGridId = 'clientes-grid';
    this.jqGridPagerId = 'clientes-pager';
    this.jqGridColNames = ['ID', 'Ver', 'Código', 'Nombre', '1º Ap.', '2º Ap.', 'CIF/NIF', 'Teléfono', 'Tipo cliente'];
    this.jqGridColModel = [
      { name: 'id', index: '', hidden: true},
      { name: '', index: '', width: '60', height: '50', align: 'center', search: false, sortable: false, formatter: () => this.botonRetorno },
      { name: 'codigo', index: '', width: '', search: true, sortable: false },
      { name: 'nombre', index: '', width: '', search: true, sortable: true },
      { name: 'apellidoPrimero', index: '', width: '', search: true, sortable: true },
      { name: 'apellidoSegundo', index: '', width: '', search: true, sortable: true },
      { name: 'nif', index: '', width: '', search: true, sortable: true },
      { name: 'telefono', index: '', width: '', search: true, sortable: true },
      { name: 'tipoCliente', index: '', width: '', search: true, sortable: true}
    ];
    this.jqGridData = new Array<Cliente>();
  }

  getListadoClientes(): void{

    console.log('Entramos en el metodo getListadoClientes()');

    this.clienteService.getClientes().then( (clientes) => {
        try {
          // Introducimos los datos
          clientes.forEach(cliente => this.jqGridData.push(cliente));
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
    ( jQuery ( '#' + this.jqGridId ) ).jqGrid({
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
      loadonce: false,
      searching: {
        multipleSearch: true,
        loadDefaults: false,
        sopt: ['eq', 'ne', 'cn', 'bw', 'bn', 'ge', 'le', 'lt', 'gt'],
        showQuery: false
      },
      guiStyle: 'bootstrap4',
      iconSet: 'fontAwesome',
      navOptions: { add: false, edit: false, search: false, del: false, refresh: true, refreshstate: 'current' },
      onCellSelect: (rowid: any, iCol: any, cellcontent: any, e: any) => {
        // Si se pulsa sobre la columna 1, pulsan sobre el boton
        console.log('Se ha pulsado sobre el boton ver para ir al cliente con id: ' + iCol);
        if (iCol === 1 )
        {
          // Obtenemos el valor de la columna oculta
          const idCelValue = jQuery( '#' + this.jqGridId ).jqGrid ('getCell', rowid, 'id');
          console.log('Se ha pulsado sobre el boton ver para ir al cliente con id: ' + idCelValue);
          this.router.navigate(['clientes', 'cliente', idCelValue]);
        }
      }
    }).jqGrid('navGrid');

    // Filtros
    jQuery('#' + this.jqGridId).jqGrid('filterToolbar', {searchOperators: false});

    // Exportar a Excel
    jQuery('#exportar').on('click', () => {

      console.log('Se inicia la exportacion a excel del listado de clientes');

      // Inicializamos la varaible
      this.tableExport = new TableExport(jQuery('#' + this.jqGridId), {
        header: true,
        footers: true,
        formats: ['xlsx', 'csv', 'txt'],
        filename : 'listado_cliente',
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
      const exportData = this.tableExport.getExportData()['clientes-grid'].xlsx;

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
    this.getListadoClientes();
  }


}
