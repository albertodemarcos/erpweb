import { Component, OnInit } from '@angular/core';

import { Factura } from 'src/app/model/entitys/factura.model';

@Component({
  selector: 'app-formulario-factura',
  templateUrl: './formulario-factura.component.html',
  styleUrls: ['./formulario-factura.component.css']
})
export class FormularioFacturaComponent implements OnInit {

  public factura: Factura;

  constructor() {

    this.factura = new Factura();

   }

  ngOnInit(): void {

  }

  // Metodos del formulario
  public crearFacturaFormulario(): void {

    console.log('Estamos dentro del metodo crear formulario');

  }

}
