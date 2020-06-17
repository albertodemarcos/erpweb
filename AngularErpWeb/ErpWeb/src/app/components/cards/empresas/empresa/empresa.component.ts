import { Component, OnInit } from '@angular/core';

import { Empresa } from 'src/app/model/entitys/empresa.model';

@Component({
  selector: 'app-empresa',
  templateUrl: './empresa.component.html',
  styleUrls: ['./empresa.component.css']
})
export class EmpresaComponent implements OnInit {

  public empresa: Empresa;

  constructor() { }

  ngOnInit(): void {
  }

}
