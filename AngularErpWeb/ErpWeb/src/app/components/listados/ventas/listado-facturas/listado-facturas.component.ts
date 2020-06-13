import { Component, OnInit } from '@angular/core';

declare var $: any;

@Component({
  selector: 'app-listado-facturas',
  templateUrl: './listado-facturas.component.html',
  styleUrls: ['./listado-facturas.component.css']
})
export class ListadoFacturasComponent implements OnInit {

  public titlePageSize: string;
  public tituloListado: string;
  public gridApi: any;

  constructor() {
    this.titlePageSize = 'Páginas';
    this.tituloListado = 'Listado de facturas';
   }

  // tslint:disable-next-line: member-ordering
  columnDefs = [
    { headerName: '', field: 'id', hide: true, cellStyle: { textAlign: 'left' } },
    { headerName: 'Código', field: 'codigo', cellStyle: { textAlign: 'left' } },
    { headerName: 'F. Inicio', field: 'fechaInicio', cellStyle: { textAlign: 'left' } },
    { headerName: 'F. Fín', field: 'fechaFin', cellStyle: { textAlign: 'left' } },
    { headerName: 'Base Impl.', field: 'baseImponibleTotal', cellStyle: { textAlign: 'left' } },
    { headerName: 'Importe Total', field: 'importeTotal', cellStyle: { textAlign: 'left' } }
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
    { id: '1', codigo: 'MAD-1', fechaInicio: '01/05/2020', fechaFin: '01/05/2021', baseImponibleTotal: 200, importeTotal: 242 }
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
