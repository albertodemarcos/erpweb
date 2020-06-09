import { Component, OnInit } from '@angular/core';

import { Cliente } from 'src/app/model/entitys/cliente.model';

@Component({
  selector: 'app-formulario-cliente',
  templateUrl: './formulario-cliente.component.html',
  styleUrls: ['./formulario-cliente.component.css']
})
export class FormularioClienteComponent implements OnInit {

  public cliente: Cliente;

  constructor() {

    this.cliente = new Cliente();

   }

  ngOnInit(): void {
  }

  // Metodos del formulario

  public crearClienteFormulario(): void {

    console.log('Estamos dentro del metodo crear formulario');

  }



}
