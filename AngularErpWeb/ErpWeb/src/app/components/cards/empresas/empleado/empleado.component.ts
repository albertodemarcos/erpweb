import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { EmpleadoService } from 'src/app/services/empresa/empleado.service';
import { Empleado } from 'src/app/model/entitys/empleado.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import swal from 'sweetalert2';


@Component({
  selector: 'app-empleado',
  templateUrl: './empleado.component.html',
  styleUrls: ['./empleado.component.css']
})
export class EmpleadoComponent implements OnInit {

  public empleado: Empleado;
  private empleadoDto: any;
  private empleadoId: number;
  private respuestaGetEmpleado: AccionRespuesta;

  constructor(private empleadoService: EmpleadoService, private router: Router, private activateRouter: ActivatedRoute) {

    this.empleadoId = 0;
    this.empleado = new Empleado();

    this.activateRouter.params.subscribe( params => {
      console.log('Entro al constructor' + params);
      // tslint:disable-next-line: no-string-literal
      this.empleadoId = params['id'];
      this.getEmpleado();
    } );
  }

  getEmpleado(): void{

    this.empleadoService.getEmpleado(this.empleadoId).toPromise().then( (empleadoDto) => {
      try
      {
        console.log('Recuperamos el Empleado');

        this.respuestaGetEmpleado = empleadoDto;

        if ( this.respuestaGetEmpleado.resultado )
        {
        // tslint:disable-next-line: no-string-literal
        this.empleadoDto = this.respuestaGetEmpleado.data['empleadoDto'];
        this.obtenerEmpleadoDesdeEmpleadoDto(this.empleadoDto);
        }

      }catch (errores){

        console.log('Se ha producido un error al transformar el Empleado' + errores);
      }
      }, (error) => {
      console.log('Error, no se ha podido recuperar el Empleado' + error);
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

  editarEmpleado(empleadoId: number): void{
    this.router.navigate(['rrhh', 'editar-empleado', empleadoId]);
  }

  borrarEmpleado(empleadoId: number): void{

    // Evitamos borrar accidentalmente un Empleado
    swal({
      title: 'Eliminar Empleado',
      text: '¿Desea eliminar definitivamente este Empleado?',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí',
      cancelButtonText: 'No'
    }).then( (resultado) => {
      // Si se pulsa en cancelar, no se continua
      if (!resultado.value) {
        return;
      }

      // Llamamos al servicio de Empleados para eliminar el Empleado
      this.empleadoService.eliminarEmpleado(empleadoId).toPromise().then( (accionRespuesta) => {

        // Si se ha eliminado correctamente
        if ( accionRespuesta.resultado ) {
        console.log('Se ha eliminado correctamente el Empleado');
        swal('Empleado eliminado', 'Se ha eliminado el Empleado correctamente', 'success').then(() =>{
          this.router.navigate( ['rrhh'] );
        });

        } else {
        console.log('Se ha producido un error al eliminar el Empleado');
        swal('Error', 'El Empleado no ha podido ser eliminado', 'error');
        }

      }, (errores) => {
        console.log('Se ha producido un error al eliminar el Empleado');
        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');
      } );
    } );

  }

  ngOnInit(): void {
  }

}
