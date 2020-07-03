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
  private clienteId: number;
  public tiposClientes: string[];
  public provincias: string[];
  public regiones: string[];
  private clienteDto: any;
  private respuestaGetCliente: AccionRespuesta;

  constructor(private clienteService: ClienteService, private router: Router, private activateRouter: ActivatedRoute) {
    this.cliente = new Cliente();
    this.tiposClientes = ['PARTICULAR', 'AUTONOMO', 'EMPRESA'];

    this.provincias = ['Álava', 'Albacete', 'Alicante', 'Almería', 'Asturias', 'Ávila', 'Badajoz', 'Barcelona', 'Burgos',
      'Cáceres', 'Cádiz', 'Cantabria', 'Castellón', 'Ciudad Real', 'Córdoba', 'La Coruña', 'Cuenca', 'Gerona', 'Granada',
      'Guadalajara', 'Guipúzcoa', 'Huelva', 'Huesca', 'Islas Baleares', 'Jaén', 'León', 'Lérida', 'Lugo', 'Madrid', 'Málaga',
      'Murcia', 'Navarra', 'Ourense', 'Palencia', 'Las Palmas', 'Pontevedra', 'La Rioja', 'Salamanca', 'Segovia', 'Sevilla',
      'Soria', 'Tarragona', 'Santa Cruz de Tenerife', 'Teruel', 'Toledo', 'Valencia', 'Valladolid', 'Vizcaya', 'Zamora', 'Zaragoza'];

    this.regiones = ['Andalucía', 'Aragón', 'Principado de Asturias', 'Islas Baleares', 'Islas Canarias', 'Cantabria', 'Castilla y León',
      'Castilla-La Mancha', 'Cataluña', 'Comunidad Valenciana', 'Extremadura', 'Galicia', 'Comunidad de Madrid', 'Región de Murcia',
      'Comunidad Foral de Navarra', 'País Vasco', 'La Rioja', 'Ciudad Autónoma de Ceuta', 'Ciudad Autónoma de Melilla' ];

    this.activateRouter.params.subscribe( params => {
      console.log('Entro al constructor' + params);
      // tslint:disable-next-line: no-string-literal
      this.clienteId = params['id'];
      if (this.clienteId != null){
        this.getEditarCliente();
      }
    });
   }

  ngOnInit(): void {
  }

  // Metodos del formulario
  public crearClienteFormulario(): void {

    console.log('Estamos dentro del metodo crearClienteFormulario()');

    // Si tiene id, llamamos a crear, sino a editar
    if (this.cliente != null && this.cliente.id != null && this.cliente.id !== 0) {

      console.log('Vamos a editar el cliente con ID: ' + this.cliente.id);

      this.clienteService.actualizarCliente(this.cliente).subscribe( accionRespuesta => {

        this.respuestaCrearEditarCliente(accionRespuesta, true);

      }, (error => {

        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');

      }));

    } else {

      this.clienteService.crearCliente(this.cliente).subscribe( accionRespuesta => {

        console.log('Vamos a crear el cliente con codigo: ' + this.cliente.codigo);

        this.respuestaCrearEditarCliente(accionRespuesta, false);

      }, (error => {

        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');

      }));
    }
  }

  getEditarCliente() {

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

  respuestaCrearEditarCliente(accionRespuesta: AccionRespuesta, esEditarCliente: boolean): void {

    console.log('Esta registrado' + accionRespuesta.resultado);
    console.log('Datos que nos devuelve spring: ' + JSON.stringify(accionRespuesta));
    // Si el resultado es true, navegamos hasta la vista
    if (accionRespuesta.resultado && accionRespuesta.id !== null ) {

      if (esEditarCliente != null && esEditarCliente ){

        swal('Cliente editado', 'Se ha editado el cliente correctamente', 'success');

        this.router.navigate(['clientes', 'cliente', accionRespuesta.id]);

       }else{

        swal('Nuevo cliente', 'Se ha creado el cliente correctamente', 'success');

        this.router.navigate(['clientes', 'cliente', accionRespuesta.id]);
       }

    }else{
      // Error
      swal('Nuevo cliente', 'Se ha producido un error al crear el cliente', 'error');
    }

  }

}




/*
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
    */
