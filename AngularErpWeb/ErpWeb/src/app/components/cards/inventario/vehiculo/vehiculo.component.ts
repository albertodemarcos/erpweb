import { Component, OnInit } from '@angular/core';

import { Vehiculo } from 'src/app/model/entitys/vehiculo.model';

@Component({
  selector: 'app-vehiculo',
  templateUrl: './vehiculo.component.html',
  styleUrls: ['./vehiculo.component.css']
})
export class VehiculoComponent implements OnInit {

  public vehiculo: Vehiculo;

  constructor() { }

  ngOnInit(): void {
  }

}
