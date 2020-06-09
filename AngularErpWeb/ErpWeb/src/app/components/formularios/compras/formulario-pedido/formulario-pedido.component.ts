import { Component, OnInit } from '@angular/core';

import { Pedido } from 'src/app/model/entitys/pedido.model';

@Component({
  selector: 'app-formulario-pedido',
  templateUrl: './formulario-pedido.component.html',
  styleUrls: ['./formulario-pedido.component.css']
})
export class FormularioPedidoComponent implements OnInit {

  public pedido: Pedido;

  constructor() {

    this.pedido = new Pedido();

   }

  ngOnInit(): void {
  }

  // Metodos del formulario
  public crearPedidoFormulario(): void {

    console.log('Estamos dentro del metodo crear formulario');

  }




}
