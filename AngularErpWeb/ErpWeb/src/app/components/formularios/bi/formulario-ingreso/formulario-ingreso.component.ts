import { Component, OnInit } from '@angular/core';

import { Ingreso } from 'src/app/model/entitys/ingreso.model';

@Component({
  selector: 'app-formulario-ingreso',
  templateUrl: './formulario-ingreso.component.html',
  styleUrls: ['./formulario-ingreso.component.css']
})
export class FormularioIngresoComponent implements OnInit {

  public ingreso: Ingreso;

  constructor() {

    this.ingreso = new Ingreso();

   }

  ngOnInit(): void {
  }

   // Metodos del formulario
  public crearIngresoFormulario(): void {

    console.log('Estamos dentro del metodo crear formulario');

  }

}
