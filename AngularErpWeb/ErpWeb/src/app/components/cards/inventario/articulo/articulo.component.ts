import { Component, OnInit } from '@angular/core';

import { Articulo } from 'src/app/model/entitys/articulo.model';

@Component({
  selector: 'app-articulo',
  templateUrl: './articulo.component.html',
  styleUrls: ['./articulo.component.css']
})
export class ArticuloComponent implements OnInit {

  public articulo: Articulo;

  constructor() { }

  ngOnInit(): void {
  }

}
