import { Component, OnInit } from '@angular/core';
import { ICellRendererAngularComp } from 'ag-grid-angular';
import { ICellRendererParams, IAfterGuiAttachedParams } from 'ag-grid';
import { Router } from '@angular/router';


@Component({
  selector: 'app-boton-listado-cliente',
  templateUrl: './boton-listado-cliente.component.html',
  styleUrls: ['./boton-listado-cliente.component.css']
})
export class BotonListadoClienteComponent implements ICellRendererAngularComp {

  params;
  label: string;

  constructor(private router: Router) {
    console.log('Se crea el componente boton ver del listado de clientes');
   }
  refresh(params: any): boolean {
    return true;
  }
  agInit(params: any): void {
    this.params = params;
    this.label = this.params.label || null;
  }
  afterGuiAttached?(params?: IAfterGuiAttachedParams): void {
    throw new Error('Method not implemented.');
  }

  onClick($event: any) {

    const datos = this.params.data;

    // Si la fila no esta vacia y tiene id, pueden ver su ficha
    if (datos != null && datos.id != null){
      const id = datos.id;
      this.router.navigate(['clientes', 'cliente', id ], { state: { cliente: datos } } ); // { queryParams: { datos: JSON.stringify(datos) } }
    }
  }

}
