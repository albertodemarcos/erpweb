import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Cliente } from 'src/app/model/entitys/cliente.model';

@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.css']
})
export class ClienteComponent implements OnInit {

  public cliente: Cliente;

  constructor(private activateRouter: ActivatedRoute) {

    this.activateRouter.params.subscribe( params => {

      console.log(params);

    } );
 

    // this.cliente = new Cliente();
     /*this.cliente = {
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
    };*/
   }

  ngOnInit(): void {

   

  }



}
