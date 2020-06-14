import { Component, OnInit } from '@angular/core';
import { BotonVerComponent } from 'src/app/components/utiles/boton-ver/boton-ver.component';


declare var $: any;

@Component({
  selector: 'app-listado-clientes',
  templateUrl: './listado-clientes.component.html',
  styleUrls: ['./listado-clientes.component.css']
})
export class ListadoClientesComponent implements OnInit {

  public titlePageSize: string;
  public tituloListado: string;
  public gridApi: any;
  public frameworkComponents: any;
  rowDataClicked1 = {};

  constructor() {
    this.titlePageSize = 'Páginas';
    this.tituloListado = 'Listado de clientes';
    this.frameworkComponents = {
      botonVer: BotonVerComponent
    };
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

  rowData = [
      { id: '1', codigo: 'MAD-1', nombre: 'Alberto', apellido: 'de Marcos', nif: '09063447K', telefono: '666777888', tipoCliente: 'Empresa' },
      { id: '1', codigo: 'GUA-1', nombre: 'Lucia', apellido: 'Alvarez', nif: '12345678L', telefono: '666777888', tipoCliente: 'Empresa' }
  ];

  onPageSizeChanged(): void {
    // tslint:disable-next-line: prefer-const
    let numeroFilas = $('page-size').val();
    this.gridApi.paginationSetPageSize(Number(numeroFilas));
  }

  onClickCellValuePopUpModal(e) {
    console.log('Paso por aqui');
    this.rowDataClicked1 = e.rowData;
    console.log('JSON: ' + JSON.stringify(e.rowData));
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
   }, 5000);*/
  }

  ngOnInit(): void {
  }

}
