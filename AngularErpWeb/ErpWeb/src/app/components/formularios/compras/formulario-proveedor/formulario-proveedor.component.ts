import { Component, OnInit } from '@angular/core';

import { Proveedor } from 'src/app/model/entitys/proveedor.model';

@Component({
  selector: 'app-formulario-proveedor',
  templateUrl: './formulario-proveedor.component.html',
  styleUrls: ['./formulario-proveedor.component.css']
})
export class FormularioProveedorComponent implements OnInit {

  public proveedor: Proveedor;

  constructor() {

    this.proveedor = new Proveedor();

   }

  ngOnInit(): void {
  }

  // Metodos del formulario
  public crearProveedorFormulario(): void {

    console.log('Estamos dentro del metodo crear formulario');

  }


}
