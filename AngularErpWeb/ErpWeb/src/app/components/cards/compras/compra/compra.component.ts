import { Component, OnInit } from '@angular/core';

import { Compra } from 'src/app/model/entitys/compra.model';

@Component({
  selector: 'app-compra',
  templateUrl: './compra.component.html',
  styleUrls: ['./compra.component.css']
})
export class CompraComponent implements OnInit {

  public compra: Compra;

  constructor() { }

  ngOnInit(): void {
  }

}
