import { Component, OnInit } from '@angular/core';

import { Proveedor } from 'src/app/model/entitys/proveedor.model';

@Component({
  selector: 'app-proveedor',
  templateUrl: './proveedor.component.html',
  styleUrls: ['./proveedor.component.css']
})
export class ProveedorComponent implements OnInit {

  public proveedor: Proveedor;

  constructor() { }

  ngOnInit(): void {
  }

}
