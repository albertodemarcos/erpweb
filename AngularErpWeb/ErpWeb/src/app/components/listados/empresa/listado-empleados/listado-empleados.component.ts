import { Component, OnInit } from '@angular/core';

declare var $: any;

@Component({
  selector: 'app-listado-empleados',
  templateUrl: './listado-empleados.component.html',
  styleUrls: ['./listado-empleados.component.css']
})
export class ListadoEmpleadosComponent implements OnInit {

  public titlePageSize: string;
  public tituloListado: string;
  public gridApi: any;

  constructor() {
    this.titlePageSize = 'Páginas';
    this.tituloListado = 'Listado de empleados';
  }

   // tslint:disable-next-line: member-ordering
  columnDefs = [
    { headerName: '', field: 'id', hide: true, cellStyle: { textAlign: 'left' } },
    { headerName: 'Código', field: 'codigo', cellStyle: { textAlign: 'left' } },
    { headerName: 'Nombre', field: 'nombre', cellStyle: { textAlign: 'left' } },
    { headerName: 'Apellido', field: 'apellido', cellStyle: { textAlign: 'left' } },
    { headerName: 'CIF/NIF', field: 'nif', cellStyle: { textAlign: 'left' } },
    { headerName: 'Teléfono', field: 'telefono', cellStyle: { textAlign: 'left' } },
    { headerName: 'Tipo empleado', field: 'tipoCliente', cellStyle: { textAlign: 'left' } }
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
    { id: '1', codigo: 'MAD-1', nombre: 'Alberto', apellido: 'de Marcos', nif: '09063447K', telefono: '666777888', tipoCliente: 'INTERNO' },
    { id: '1', codigo: 'GUA-1', nombre: 'Lucia', apellido: 'Alvarez', nif: '12345678L', telefono: '666777888', tipoCliente: 'EXTERNO' }
  ];

  onPageSizeChanged(): void {
    // tslint:disable-next-line: prefer-const
    let numeroFilas = $('page-size').val();
    this.gridApi.paginationSetPageSize(Number(numeroFilas));
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
