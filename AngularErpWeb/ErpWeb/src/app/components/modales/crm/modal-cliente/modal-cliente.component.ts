import { Component, OnInit } from '@angular/core';

import { Cliente } from 'src/app/model/entitys/cliente.model';

@Component({
  selector: 'app-modal-cliente',
  templateUrl: './modal-cliente.component.html',
  styleUrls: ['./modal-cliente.component.css']
})
export class ModalClienteComponent implements OnInit {

  public cliente: Cliente;

  constructor() {
    // this.cliente = new Cliente();
    this.cliente = {
      id: 1,
      codigo: 'CD1',
      nombre: 'Alberto',
      apellidoPrimero: 'de Marcos',
      apellidoSegundo: 'Molina',
      nif: '09063447K',
      codigoPostal: '28816',
      direccion: 'La noria 44',
      edificio: '',
      observaciones: '',
      telefono: '679213821',
      poblacion: 'Camarma de esteruelas',
      region: 'C. Madrid',
      provincia: 'Madrid',
      pais: 'España',
      tipoCliente: 'Empresa'
    };

   }

  ngOnInit(): void {
    console.log('Entro' + JSON.stringify(this.cliente));
  }

}
