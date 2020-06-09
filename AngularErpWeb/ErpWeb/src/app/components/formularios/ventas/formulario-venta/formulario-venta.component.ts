import { Component, OnInit } from '@angular/core';

import { Venta } from 'src/app/model/entitys/venta.model';

@Component({
  selector: 'app-formulario-venta',
  templateUrl: './formulario-venta.component.html',
  styleUrls: ['./formulario-venta.component.css']
})
export class FormularioVentaComponent implements OnInit {

  public venta: Venta;

  constructor() {

    this.venta = new Venta();

  }

  ngOnInit(): void {
  }

   // Metodos del formulario

  public crearVentaFormulario(): void {

    console.log('Estamos dentro del metodo crear formulario');

  }




}
