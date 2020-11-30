import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { EmpleadoService } from 'src/app/services/empresa/empleado.service';
import { Empleado } from 'src/app/model/entitys/empleado.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import swal from 'sweetalert2';

@Component({
  selector: 'app-formulario-empleado',
  templateUrl: './formulario-empleado.component.html',
  styleUrls: ['./formulario-empleado.component.css']
})
export class FormularioEmpleadoComponent implements OnInit {

  public empleado: Empleado;
  private empresaId: number;
  public provincias: string[];
  public regiones: string[];
  public tiposEmpleados: string[];
  private empleadoDto: any;
  private respuestaGetCliente: AccionRespuesta;
  public erroresFormulario: Map<string, object>;
  public titulo: string;
  public botonTitulo: string;

  constructor(private empleadoService: EmpleadoService, private router: Router, private activateRouter: ActivatedRoute) {

    this.empleado = new Empleado();
    this.tiposEmpleados = ['INTERNO', 'EXTERNO'];

    this.provincias = ['Álava', 'Albacete', 'Alicante', 'Almería', 'Asturias', 'Ávila', 'Badajoz', 'Barcelona', 'Burgos',
      'Cáceres', 'Cádiz', 'Cantabria', 'Castellón', 'Ciudad Real', 'Córdoba', 'La Coruña', 'Cuenca', 'Gerona', 'Granada',
      'Guadalajara', 'Guipúzcoa', 'Huelva', 'Huesca', 'Islas Baleares', 'Jaén', 'León', 'Lérida', 'Lugo', 'Madrid', 'Málaga',
      'Murcia', 'Navarra', 'Ourense', 'Palencia', 'Las Palmas', 'Pontevedra', 'La Rioja', 'Salamanca', 'Segovia', 'Sevilla',
      'Soria', 'Tarragona', 'Santa Cruz de Tenerife', 'Teruel', 'Toledo', 'Valencia', 'Valladolid', 'Vizcaya', 'Zamora', 'Zaragoza'];

    this.regiones = ['Andalucía', 'Aragón', 'Principado de Asturias', 'Islas Baleares', 'Islas Canarias', 'Cantabria', 'Castilla y León',
      'Castilla-La Mancha', 'Cataluña', 'Comunidad Valenciana', 'Extremadura', 'Galicia', 'Comunidad de Madrid', 'Región de Murcia',
      'Comunidad Foral de Navarra', 'País Vasco', 'La Rioja', 'Ciudad Autónoma de Ceuta', 'Ciudad Autónoma de Melilla' ];

    this.erroresFormulario = new Map<string, object>();
    this.titulo = 'Nuevo empleado';
    this.botonTitulo = 'Crear empleado';

    this.activateRouter.params.subscribe( params => {
      console.log('Entro al constructor' + params);
      // tslint:disable-next-line: no-string-literal
      this.empresaId = params['id'];
      if (this.empresaId != null){
        this.getEditarEmpleado();
      }
    } );

  }

  ngOnInit(): void {
  }


   // Metodos del formulario
  public crearEmpleadoFormulario(): void {

    console.log('Estamos dentro del metodo crearEmpleadoFormulario()');

    // Si tiene id, llamamos a crear, sino a editar
    if (this.empleado != null && this.empleado.id != null && this.empleado.id !== 0) {

      console.log('Vamos a editar el empleado con ID: ' + this.empleado.id);

      this.empleadoService.actualizarEmpleado(this.empleado).subscribe( accionRespuesta => {

        this.respuestaCrearEditarEmpleado(accionRespuesta, true);

      }, (error => {

        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');

      }));

    } else {

      this.empleadoService.crearEmpleado(this.empleado).subscribe( accionRespuesta => {

        console.log('Vamos a crear el empleado con codigo: ' + this.empleado.codigo);

        this.respuestaCrearEditarEmpleado(accionRespuesta, false);

      }, (error => {

        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');

      }));
    }

  }

  getEditarEmpleado() {

    this.empleadoService.getEmpleado(this.empresaId).toPromise().then( (accionRespuesta) => {
        try
        {
          this.respuestaGetCliente = accionRespuesta;

          if ( this.respuestaGetCliente.resultado )
          {
            // tslint:disable-next-line: no-string-literal
            this.empleadoDto = this.respuestaGetCliente.data['empleadoDto'];
            this.obtenerEmpleadoDesdeEmpleadoDto(this.empleadoDto);
            this.titulo = 'Editar empleado';
            this.botonTitulo = 'Editar empleado';
          }

        }catch (errores){

          console.log('Se ha producido un error al transformar el empleado' + errores);
        }
      }, (error) => {
        console.log('Error, no se ha podido recuperar el cliente' + error);
      }
    );
  }

  obtenerEmpleadoDesdeEmpleadoDto(empleadoDto: any): void{

    if ( empleadoDto != null)
    {
      this.empleado.id = empleadoDto.id;
      this.empleado.codigo = empleadoDto.codigo;
      this.empleado.nombre = empleadoDto.nombre;
      this.empleado.apellidoPrimero = empleadoDto.apellidoPrimero;
      this.empleado.apellidoSegundo = empleadoDto.apellidoSegundo;
      this.empleado.nif = empleadoDto.nif;
      this.empleado.codigoPostal = empleadoDto.codigoPostal;
      this.empleado.direccion = empleadoDto.direccion;
      this.empleado.edificio = empleadoDto.edificio;
      this.empleado.observaciones = empleadoDto.observaciones;
      this.empleado.telefono = empleadoDto.telefono;
      this.empleado.poblacion = empleadoDto.poblacion;
      this.empleado.region = empleadoDto.region;
      this.empleado.provincia = empleadoDto.provincia;
      this.empleado.pais = empleadoDto.pais;
      this.empleado.tipoEmpleado = empleadoDto.tipoEmpleado;
    }
  }

  respuestaCrearEditarEmpleado(accionRespuesta: AccionRespuesta, esEditarEmpleado: boolean): void {

    // Si el resultado es true, navegamos hasta la vista
    if (accionRespuesta.resultado && accionRespuesta.id !== null ) {

      this.router.navigate(['rrhh', 'empleado', accionRespuesta.id]);

      if (esEditarEmpleado != null && esEditarEmpleado ){

        swal('Empleado editado', 'Se ha editado el empleado correctamente', 'success');

       }else{

        swal('Nuevo empleado', 'Se ha creado el empleado correctamente', 'success');

       }

    }else{

      if ( accionRespuesta != null && accionRespuesta.data != null && accionRespuesta.data !=  null )
      {
        this.erroresFormulario = accionRespuesta.data;
      }else
      {
        swal('Error', 'Se ha producido un error al guardar los datos del empleado', 'error');
      }
    }

  }

}
