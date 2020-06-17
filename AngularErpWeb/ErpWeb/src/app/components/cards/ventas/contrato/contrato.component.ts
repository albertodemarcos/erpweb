import { Component, OnInit } from '@angular/core';

import { Contrato } from 'src/app/model/entitys/contrato.model';

@Component({
  selector: 'app-contrato',
  templateUrl: './contrato.component.html',
  styleUrls: ['./contrato.component.css']
})
export class ContratoComponent implements OnInit {

  public contrato: Contrato;

  constructor() { }

  ngOnInit(): void {
  }

}
