import { Component, OnInit } from '@angular/core';

import { Empresa } from 'src/app/model/entitys/empresa.model';

@Component({
  selector: 'app-formulario-empresa',
  templateUrl: './formulario-empresa.component.html',
  styleUrls: ['./formulario-empresa.component.css']
})
export class FormularioEmpresaComponent implements OnInit {

  public empresa: Empresa;

  constructor() {

    this.empresa = new Empresa();

   }

  ngOnInit(): void {

  }

 // Metodos del formulario
 public crearEmpresaFormulario(): void {

  console.log('Estamos dentro del metodo crear formulario');

 }



}
