import { Component, OnInit } from '@angular/core';

import { Gasto } from 'src/app/model/entitys/gasto.model';

@Component({
  selector: 'app-formulario-gasto',
  templateUrl: './formulario-gasto.component.html',
  styleUrls: ['./formulario-gasto.component.css']
})
export class FormularioGastoComponent implements OnInit {

  public gasto: Gasto;

  constructor() {

    this.gasto = new Gasto();

  }

  ngOnInit(): void {
  }

  // Metodos del formulario
  public crearGastoFormulario(): void {

    console.log('Estamos dentro del metodo crear formulario');

  }

}
