import { Component, OnInit } from '@angular/core';

import { Empleado } from 'src/app/model/entitys/empleado.model';

@Component({
  selector: 'app-empleado',
  templateUrl: './empleado.component.html',
  styleUrls: ['./empleado.component.css']
})
export class EmpleadoComponent implements OnInit {

  public empleado: Empleado;

  constructor() { }

  ngOnInit(): void {
  }

}
