import { Component, OnInit } from '@angular/core';

import { Vehiculo } from 'src/app/model/entitys/vehiculo.model';

@Component({
  selector: 'app-formulario-vehiculo',
  templateUrl: './formulario-vehiculo.component.html',
  styleUrls: ['./formulario-vehiculo.component.css']
})
export class FormularioVehiculoComponent implements OnInit {

  public vehiculo: Vehiculo;

  constructor() {

    this.vehiculo = new Vehiculo();

   }

  ngOnInit(): void {
  }

  // Metodos del formulario
  public crearVehiculoFormulario(): void {

    console.log('Estamos dentro del metodo crear formulario');

  }

}
