import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ClienteService } from 'src/app/services/crm/cliente.service';
import { Cliente } from 'src/app/model/entitys/cliente.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import swal from 'sweetalert2';

@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.css']
})
export class ClienteComponent implements OnInit {

  public cliente: Cliente;
  private clienteDto: any;
  private clienteId: number;
  private respuestaGetCliente: AccionRespuesta;

  constructor(private clienteService: ClienteService, private router: Router, private activateRouter: ActivatedRoute) {

    this.clienteId = 0;
    this.cliente = new Cliente();

    this.activateRouter.params.subscribe( params => {
      console.log('Entro al constructor' + params);
      // tslint:disable-next-line: no-string-literal
      this.clienteId = params['id'];
      this.getCliente();
    } );
  }

  getCliente(): void{

    this.clienteService.getCliente(this.clienteId).toPromise().then( (accionRespuesta) => {
        try
        {
          console.log('Recuperamos el cliente');

          this.respuestaGetCliente = accionRespuesta;

          if ( this.respuestaGetCliente.resultado )
          {
            console.log('Respuesta: ' +  JSON.stringify(this.respuestaGetCliente.data) );
            console.log('ES: ' + typeof(this.respuestaGetCliente.data));
            // tslint:disable-next-line: no-string-literal
            this.clienteDto = this.respuestaGetCliente.data['clienteDto'];
            this.obtenerClienteDesdeClienteDto(this.clienteDto);
          }

        }catch (errores){

          console.log('Se ha producido un error al transformar el cliente' + errores);
        }
      }, (error) => {
        console.log('Error, no se ha podido recuperar el cliente' + error);
      }
    );
  }

  obtenerClienteDesdeClienteDto(clienteDto: any): void{

    if ( clienteDto != null)
    {
      this.cliente.id = clienteDto.id;
      this.cliente.codigo = clienteDto.codigo;
      this.cliente.nombre = clienteDto.nombre;
      this.cliente.apellidoPrimero = clienteDto.apellidoPrimero;
      this.cliente.apellidoSegundo = clienteDto.apellidoSegundo;
      this.cliente.nif = clienteDto.nif;
      this.cliente.codigoPostal = clienteDto.codigoPostal;
      this.cliente.direccion = clienteDto.direccion;
      this.cliente.edificio = clienteDto.edificio;
      this.cliente.observaciones = clienteDto.observaciones;
      this.cliente.telefono = clienteDto.telefono;
      this.cliente.poblacion = clienteDto.poblacion;
      this.cliente.region = clienteDto.region;
      this.cliente.provincia = clienteDto.provincia;
      this.cliente.pais = clienteDto.pais;
      this.cliente.tipoCliente = clienteDto.tipoCliente;
    }
  }

  editarCliente(clienteId: number): void{
    console.log('CLIENTE CON ID: ' + clienteId);
    this.router.navigate(['clientes', 'editar-cliente', clienteId]);
  }

  borrarCliente(clienteId: number): void{

    console.log('CLIENTE CON ID: ' + clienteId);

    // Evitamos borrar accidentalmente un cliente
    swal({
          title: 'Eliminar cliente',
          text: '¿Desea eliminar definitivamente este cliente?',
          type: 'warning',
          showCancelButton: true,
          confirmButtonText: 'Sí',
          cancelButtonText: 'No'
        }).then( (resultado) => {
            // Si se pulsa en cancelar, no se continua
            if (!resultado.value) {
              return;
            }

            // Llamamos al servicio de clientes para eliminar el cliente
            this.clienteService.eliminarCliente(clienteId).toPromise().then( (accionRespuesta) => {

              // Si se ha eliminado correctamente
              if ( accionRespuesta.resultado ) {
                console.log('Se ha eliminado correctamente el cliente');
                swal('Cliente eliminado', 'Se ha eliminado el cliente correctamente', 'success').then(() =>{
                  this.router.navigate( ['clientes'] );
                });

              } else {
                console.log('Se ha producido un error al eliminar el cliente');
                swal('Error', 'El cliente no ha podido ser eliminado', 'error');
              }

            }, (errores) => {
              console.log('Se ha producido un error al eliminar el cliente');
              swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');
            } );
        } );

  }

  ngOnInit(): void {

  }

}
