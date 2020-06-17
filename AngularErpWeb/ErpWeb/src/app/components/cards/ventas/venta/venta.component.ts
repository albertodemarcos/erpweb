import { Component, OnInit } from '@angular/core';

import { Venta } from 'src/app/model/entitys/venta.model';


@Component({
  selector: 'app-venta',
  templateUrl: './venta.component.html',
  styleUrls: ['./venta.component.css']
})
export class VentaComponent implements OnInit {

  public venta: Venta;

  constructor() { }

  ngOnInit(): void {
  }

}
