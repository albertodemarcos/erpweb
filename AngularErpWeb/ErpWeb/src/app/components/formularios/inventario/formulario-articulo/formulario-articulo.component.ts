import { Component, OnInit } from '@angular/core';

import { Articulo } from 'src/app/model/entitys/articulo.model';

@Component({
  selector: 'app-formulario-articulo',
  templateUrl: './formulario-articulo.component.html',
  styleUrls: ['./formulario-articulo.component.css']
})
export class FormularioArticuloComponent implements OnInit {

  public articulo: Articulo;

  constructor() {

    this.articulo = new Articulo();

   }

  ngOnInit(): void {
  }

  // Metodos del formulario
  public crearArticuloFormulario(): void {

    console.log('Estamos dentro del metodo crear formulario');

  }

}
