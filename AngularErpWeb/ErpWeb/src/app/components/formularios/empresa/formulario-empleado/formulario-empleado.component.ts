import { Component, OnInit } from '@angular/core';

import { Empleado } from 'src/app/model/entitys/empleado.model';

@Component({
  selector: 'app-formulario-empleado',
  templateUrl: './formulario-empleado.component.html',
  styleUrls: ['./formulario-empleado.component.css']
})
export class FormularioEmpleadoComponent implements OnInit {

  public empleado: Empleado;


  constructor() {

    this.empleado = new Empleado();

  }

  ngOnInit(): void {
  }


   // Metodos del formulario

   public crearEmpleadoFormulario(): void {

    console.log('Estamos dentro del metodo crear formulario');

  }

}
