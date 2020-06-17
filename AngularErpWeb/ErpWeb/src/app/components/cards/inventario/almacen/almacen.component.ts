import { Component, OnInit } from '@angular/core';

import { Almacen } from 'src/app/model/entitys/almacen.model';

@Component({
  selector: 'app-almacen',
  templateUrl: './almacen.component.html',
  styleUrls: ['./almacen.component.css']
})
export class AlmacenComponent implements OnInit {

  public almacen: Almacen;

  constructor() { }

  ngOnInit(): void {
  }

}
