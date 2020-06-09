import { Component, OnInit } from '@angular/core';

import { Compra } from 'src/app/model/entitys/compra.model';

@Component({
  selector: 'app-formulario-compra',
  templateUrl: './formulario-compra.component.html',
  styleUrls: ['./formulario-compra.component.css']
})
export class FormularioCompraComponent implements OnInit {

  public compra: Compra;

  constructor() {

    this.compra = new Compra();

  }

  ngOnInit(): void {

  }

  // Metodos del formulario
  public crearCompraFormulario(): void {

    console.log('Estamos dentro del metodo crear formulario');

  }




}
