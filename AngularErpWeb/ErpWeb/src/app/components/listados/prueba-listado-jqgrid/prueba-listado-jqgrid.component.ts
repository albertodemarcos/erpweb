import { Component, OnInit, AfterViewInit } from '@angular/core';

declare var jQuery: any;

@Component({
  selector: 'app-prueba-listado-jqgrid',
  templateUrl: './prueba-listado-jqgrid.component.html',
  styleUrls: ['./prueba-listado-jqgrid.component.css']
})
export class PruebaListadoJqgridComponent implements OnInit, AfterViewInit {

  constructor() { }

  ngOnInit(): void {
  }

  ngAfterViewInit(): void {

    ( jQuery ('#grid') ).jqGrid({
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

  }

}
