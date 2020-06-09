import { Component, OnInit } from '@angular/core';

import { Contrato } from 'src/app/model/entitys/contrato.model';

@Component({
  selector: 'app-formulario-contrato',
  templateUrl: './formulario-contrato.component.html',
  styleUrls: ['./formulario-contrato.component.css']
})
export class FormularioContratoComponent implements OnInit {

  public contrato: Contrato;

  constructor() {

    this.contrato = new Contrato();

  }

  ngOnInit(): void {
  }

  // Metodos del formulario
  public crearContratoFormulario(): void {

    console.log('Estamos dentro del metodo crear formulario');

  }




}
