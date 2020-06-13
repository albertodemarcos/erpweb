import { Component, OnInit } from '@angular/core';

declare var $: any;

@Component({
  selector: 'app-listado-almacen',
  templateUrl: './listado-almacen.component.html',
  styleUrls: ['./listado-almacen.component.css']
})
export class ListadoAlmacenComponent implements OnInit {

  public titlePageSize: string;
  public tituloListado: string;
  public gridApi: any;

  constructor() {
    this.titlePageSize = 'Páginas';
    this.tituloListado = 'Listado de almacenes';
  }

  // tslint:disable-next-line: member-ordering
  columnDefs = [
    { headerName: 'Make', field: 'make' },
    { headerName: 'Model', field: 'model' },
    { headerName: 'Price', field: 'price' }
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
      { make: 'Toyota', model: 'Celica', price: 35000 },
      { make: 'Ford', model: 'Mondeo', price: 32000 },
      { make: 'Porsche', model: 'Boxter', price: 72000 },
      { make: 'Toyota', model: 'Celica', price: 35000 },
      { make: 'Ford', model: 'Mondeo', price: 32000 },
      { make: 'Porsche', model: 'Boxter', price: 72000 },
      { make: 'Toyota', model: 'Celica', price: 35000 },
      { make: 'Ford', model: 'Mondeo', price: 32000 },
      { make: 'Porsche', model: 'Boxter', price: 72000 },
      { make: 'Toyota', model: 'Celica', price: 35000 },
      { make: 'Ford', model: 'Mondeo', price: 32000 },
      { make: 'Porsche', model: 'Boxter', price: 72000 }
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
