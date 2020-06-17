import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { ClienteService } from 'src/app/services/crm/cliente.service';
import { Cliente } from 'src/app/model/entitys/cliente.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';

@Component({
  selector: 'app-formulario-cliente',
  templateUrl: './formulario-cliente.component.html',
  styleUrls: ['./formulario-cliente.component.css']
})
export class FormularioClienteComponent implements OnInit {

  public cliente: Cliente;

  constructor(private clienteService: ClienteService, private router: Router) {

    this.cliente = new Cliente();

   }

  ngOnInit(): void {
  }

  // Metodos del formulario
  public crearClienteFormulario(): void {

    console.log('Estamos dentro del metodo crear formulario');

    this.clienteService.crearCliente(this.cliente).subscribe( accionRespuesta => {
      console.log('Esta registrado' + accionRespuesta.resultado);
    });

  }



}
