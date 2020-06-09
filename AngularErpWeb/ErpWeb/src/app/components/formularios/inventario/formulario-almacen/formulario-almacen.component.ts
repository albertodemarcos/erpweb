import { Component, OnInit } from '@angular/core';

import { Almacen } from 'src/app/model/entitys/almacen.model';

@Component({
  selector: 'app-formulario-almacen',
  templateUrl: './formulario-almacen.component.html',
  styleUrls: ['./formulario-almacen.component.css']
})
export class FormularioAlmacenComponent implements OnInit {

  public almacen: Almacen;

  constructor() {

    this.almacen = new Almacen();

   }

  ngOnInit(): void {
  }

  // Metodos del formulario
  public crearAlmacenFormulario(): void {

    console.log('Estamos dentro del metodo crear formulario');

  }



}
