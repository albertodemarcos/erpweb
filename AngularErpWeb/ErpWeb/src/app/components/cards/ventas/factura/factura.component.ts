import { Component, OnInit } from '@angular/core';

import { Factura } from 'src/app/model/entitys/factura.model';

@Component({
  selector: 'app-factura',
  templateUrl: './factura.component.html',
  styleUrls: ['./factura.component.css']
})
export class FacturaComponent implements OnInit {

  public factura: Factura;

  constructor() { }

  ngOnInit(): void {
  }

}
