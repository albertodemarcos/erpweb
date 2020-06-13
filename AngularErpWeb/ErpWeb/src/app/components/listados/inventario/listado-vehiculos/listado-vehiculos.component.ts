import { Component, OnInit } from '@angular/core';

declare var $: any;

@Component({
  selector: 'app-listado-vehiculos',
  templateUrl: './listado-vehiculos.component.html',
  styleUrls: ['./listado-vehiculos.component.css']
})
export class ListadoVehiculosComponent implements OnInit {

  public titlePageSize: string;
  public tituloListado: string;
  public gridApi: any;

  constructor() {
    this.titlePageSize = 'Páginas';
    this.tituloListado = 'Listado de vehículos';
   }

  // tslint:disable-next-line: member-ordering
  columnDefs = [
    { headerName: '', field: 'id', hide: true, cellStyle: { textAlign: 'left' } },
    { headerName: 'Código', field: 'codigo', cellStyle: { textAlign: 'left' } },
    { headerName: 'Marca', field: 'marca', cellStyle: { textAlign: 'left' } },
    { headerName: 'Modelo', field: 'modelo', cellStyle: { textAlign: 'left' } },
    { headerName: 'Matricula', field: 'matricula', cellStyle: { textAlign: 'left' } },
    { headerName: 'Tipo', field: 'tipoVehiculo', cellStyle: { textAlign: 'left' } },
    { headerName: 'Combustible', field: 'tipoCombustible', cellStyle: { textAlign: 'left' } },
    { headerName: 'F. matriculación', field: 'fechaMatriculacion', cellStyle: { textAlign: 'left' } },
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
    { id: '1', codigo: 'MAD-1', marca: 'Ford', modelo: 'Mondeo', matricula: '1234LKH', tipoVehiculo: 'Compacto', tipoCombustible: 'Gasoil', fechaMatriculacion: '02/02/2020' }
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
