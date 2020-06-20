import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ClienteService } from 'src/app/services/crm/cliente.service';
import { Cliente } from 'src/app/model/entitys/cliente.model';

@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.css']
})
export class ClienteComponent implements OnInit {

  public cliente: Cliente;

  constructor(private clienteService: ClienteService, private activateRouter: ActivatedRoute) {

    if (clienteService.getClienteAux() != null){
      this.cliente = clienteService.getClienteAux();
    }
    /*this.activateRouter.params.subscribe( params => {
      console.log(params);
    } );*/
  }

  ngOnInit(): void {



  }



}
