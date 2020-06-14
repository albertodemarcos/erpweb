import { Component, OnInit, OnDestroy } from '@angular/core';
import { ICellRendererAngularComp } from 'ag-grid-angular';
import { ICellRendererParams, IAfterGuiAttachedParams } from 'ag-grid';


@Component({
  selector: 'app-boton-ver',
  templateUrl: './boton-ver.component.html',
  styleUrls: ['./boton-ver.component.css']
})
export class BotonVerComponent implements ICellRendererAngularComp {

  params;
  label: string;

  constructor() {
    console.log('Se crea el componente boton ver');
  }

  agInit(params: any): void {
    this.params = params;
    this.label = this.params.label || null;
  }

  refresh(params?: any): boolean {
    return true;
  }

  afterGuiAttached?(params?: import('ag-grid-community').IAfterGuiAttachedParams): void {
    console.log('Metodo raro');
    throw new Error('Method not implemented.');
  }

  onClick($event: any) {

    console.log('Click' + this.params);

    alert('Datos: ' + JSON.stringify(this.params.data) );

  }


}
