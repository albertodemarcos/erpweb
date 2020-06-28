import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { ClienteService } from 'src/app/services/crm/cliente.service';
import { Cliente } from 'src/app/model/entitys/cliente.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import swal from 'sweetalert2';


@Component({
  selector: 'app-formulario-cliente',
  templateUrl: './formulario-cliente.component.html',
  styleUrls: ['./formulario-cliente.component.css']
})
export class FormularioClienteComponent implements OnInit {

  public cliente: Cliente;
  public tiposClientes: string[];

  constructor(private clienteService: ClienteService, private router: Router) {
    this.cliente = new Cliente();
    this.tiposClientes = ['PARTICULAR', 'AUTONOMO', 'EMPRESA'];
   }

  ngOnInit(): void {
  }

  // Metodos del formulario
  public crearClienteFormulario(): void {

    console.log('Estamos dentro del metodo crear formulario');

    this.clienteService.crearCliente(this.cliente).subscribe( accionRespuesta => {
      console.log('Esta registrado' + accionRespuesta.resultado);
      console.log('Datos que nos devuelve spring: ' + JSON.stringify(accionRespuesta));
      // Si el resultado es true, navegamos hasta la vista
      if (accionRespuesta.resultado && accionRespuesta.id !== null ) {

        this.router.navigate(['clientes', 'cliente', accionRespuesta.id]);

        swal('Nuevo cliente', 'Se ha creado el cliente correctamente', 'success');

      }else{

        swal('Nuevo cliente', 'Error, no se ha podido crear el cliente', 'error');
      }
    }, (error => {

      swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');

    }));

  }



}
