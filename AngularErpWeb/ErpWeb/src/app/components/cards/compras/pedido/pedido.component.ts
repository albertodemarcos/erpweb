import { Component, OnInit } from '@angular/core';

import { Pedido } from 'src/app/model/entitys/pedido.model';

@Component({
  selector: 'app-pedido',
  templateUrl: './pedido.component.html',
  styleUrls: ['./pedido.component.css']
})
export class PedidoComponent implements OnInit {

  public pedido: Pedido;

  constructor() { }

  ngOnInit(): void {
  }

}
