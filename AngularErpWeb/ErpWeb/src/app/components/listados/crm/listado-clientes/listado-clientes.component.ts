import { Component, OnInit , AfterViewInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { first } from 'rxjs/operators';
import { ClienteService } from 'src/app/services/crm/cliente.service';
import { Cliente } from 'src/app/model/entitys/cliente.model';

declare var jQuery: any;

@Component({
  selector: 'app-listado-clientes',
  templateUrl: './listado-clientes.component.html',
  styleUrls: ['./listado-clientes.component.css']
})
export class ListadoClientesComponent implements OnInit, AfterViewInit {

  public tituloListado: string;
  private jqGridId: string;
  private jqGridPagerId: string;
  private jqGridUrl: string;
  private jqGridColNames: string[];
  private jqGridColModel: {};
  private jqGridData: Cliente[];

  constructor(private clienteService: ClienteService, private router: Router, private activatedRoute: ActivatedRoute) {
    this.tituloListado = 'Listado de clientes';
    this.jqGridUrl = 'http://localhost:8080/clientes/listado.json';
    this.jqGridId = 'clientes-grid';
    this.jqGridPagerId = 'clientes-pager';
    this.jqGridColNames = ['', 'Código', 'Nombre', 'Apellido', 'CIF/NIF', 'Teléfono', 'Tipo cliente'];
    this.jqGridColModel = [
      { name: 'id', index: '', width: '40', search: false, sortable: false },
      { name: 'codigo', index: '', width: '', search: true, sortable: true },
      { name: 'nombre', index: '', width: '', search: true, sortable: true },
      { name: 'apellido', index: '', width: '', search: true, sortable: true },
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

    console.log('JqGrid');

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

    jQuery('#' + this.jqGridId).jqGrid('filterToolbar', {searchOperators : true});

  }

  ngOnInit(): void {
    this.getListadoClientes();
  }

  /*
  datatype: 'json',
      url: this.jqGridUrl + '.json',
   ( $('#grid') ).jqGrid({
      colModel: [
          { name: 'firstName' },
          { name: 'lastName' }
      ],
      data: [
          { id: 10, firstName: 'Angela', lastName: 'Merkel' },
          { id: 20, firstName: 'Vladimir', lastName: 'Putin' },
          { id: 30, firstName: 'David', lastName: 'Cameron' },
          { id: 40, firstName: 'Barack', lastName: 'Obama' },
          { id: 50, firstName: 'François', lastName: 'Hollande' }
      ]
    });
   */



  /*
  import { BotonListadoClienteComponent } from 'src/app/components/utiles/botonesListado/boton-listado-cliente/boton-listado-cliente.component';
   public titlePageSize: string;
  public tituloListado: string;
  public gridApi: any;
  public frameworkComponents: any;
  public rowData: Cliente[];
  public rowDataClicked1 = {};

  constructor(private clienteService: ClienteService, private router: Router, private activatedRoute: ActivatedRoute) {
    this.titlePageSize = 'Páginas';
    this.tituloListado = 'Listado de clientes';
    this.frameworkComponents = {
      botonVer: BotonListadoClienteComponent
    };
   }

   ngOnInit(): void {
    // this.getListadoClientes();
  }
  // tslint:disable-next-line: member-ordering
  columnDefs = [
    { headerName: 'Cliente',
      cellRenderer: 'botonVer',
      cellRendererParams: {
        onClick: this.onClickCellValuePopUpModal(this),
        label: 'VER ESTO'
      },
      cellStyle: { textAlign: 'left' }
    },

    { headerName: 'Código', field: 'codigo', cellStyle: { textAlign: 'left' } },
    { headerName: 'Nombre', field: 'nombre', cellStyle: { textAlign: 'left' } },
    { headerName: 'Apellido', field: 'apellido', cellStyle: { textAlign: 'left' } },
    { headerName: 'CIF/NIF', field: 'nif', cellStyle: { textAlign: 'left' } },
    { headerName: 'Teléfono', field: 'telefono', cellStyle: { textAlign: 'left' } },
    { headerName: 'Tipo cliente', field: 'tipoCliente', cellStyle: { textAlign: 'left' } }
  ];

  defaultColDef = {
    sortingOrder: ['desc', 'asc'],
    sortable: true,
    filter: true,
    resizable: true,
    editable: false,
    flex: 1,
    minWidth: 25,
  };
  */
  /*rowData = [
      { id: '1', codigo: 'MAD-1', nombre: 'Alberto', apellido: 'de Marcos', nif: '09063447K', telefono: '666777888', tipoCliente: 'Empresa' },
      { id: '1', codigo: 'GUA-1', nombre: 'Lucia', apellido: 'Alvarez', nif: '12345678L', telefono: '666777888', tipoCliente: 'Empresa' }
  ];*/

  /*onPageSizeChanged(): void {
    // tslint:disable-next-line: prefer-const
    let numeroFilas = $('page-size').val();
    this.gridApi.paginationSetPageSize(Number(numeroFilas));
  }

  onClickCellValuePopUpModal(e: any) {
    console.log('Paso por aqui');
    this.rowDataClicked1 = e.rowData;
    console.log('JSON: ' + JSON.stringify(e.rowData));
  }

  getListadoClientes(): void{

    console.log('Entramos en el metodo getListadoClientes()');

    this.clienteService.getClientes().then( (clientes) => {
        try {
          this.rowData = clientes;
        } catch (errores){
          console.error('Se ha producido un error al convertir la infomracion del servidor' + errores);
        }
      }, (error) => {
        console.log('Error, no se ha obtenido la informacion');
      }
    );
  }

  onGridReady() {
   /*setTimeout(function(){
      var selector = '<div class="example-header">Page Size:
      <select (change)="onPageSizeChanged()" id="page-size">
      <option value="10" selected="">10</option><option value="100">100
      </option><option value="500">500</option><option value="1000">1000</option>
      </select></div>';
      // tslint:disable-next-line: align
      $('ag-paging-panel ag-unselectable').append( selector );
      // tslint:disable-next-line: align
      console.log("Hay: " + $('ag-paging-panel ag-unselectable').hide());
   }, 5000);
  }
  */
  

}
