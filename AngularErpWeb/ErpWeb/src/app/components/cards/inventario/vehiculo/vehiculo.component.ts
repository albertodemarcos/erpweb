import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { VehiculoService } from 'src/app/services/inventario/vehiculo.service';
import { Vehiculo } from 'src/app/model/entitys/vehiculo.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import swal from 'sweetalert2';


@Component({
  selector: 'app-vehiculo',
  templateUrl: './vehiculo.component.html',
  styleUrls: ['./vehiculo.component.css']
})
export class VehiculoComponent implements OnInit {

  public vehiculo: Vehiculo;
  private vehiculoDto: any;
  private vehiculoId: number;
  private respuestaGetVehiculo: AccionRespuesta;

  constructor(private vehiculoService: VehiculoService, private router: Router, private activateRouter: ActivatedRoute) {

    this.vehiculoId = 0;
    this.vehiculo = new Vehiculo();

    this.activateRouter.params.subscribe( params => {
      console.log('Entro al constructor' + params);
      // tslint:disable-next-line: no-string-literal
      this.vehiculoId = params['id'];
      this.getVehiculo();
    } );
  }

  getVehiculo(): void{

    this.vehiculoService.getVehiculo(this.vehiculoId).toPromise().then( (vehiculoDto) => {
      try
      {
        console.log('Recuperamos el Vehiculo');

        this.respuestaGetVehiculo = vehiculoDto;

        if ( this.respuestaGetVehiculo.resultado )
        {
        console.log('Respuesta: ' +  JSON.stringify(this.respuestaGetVehiculo.data) );
        console.log('ES: ' + typeof(this.respuestaGetVehiculo.data));
        // tslint:disable-next-line: no-string-literal
        this.vehiculoDto = this.respuestaGetVehiculo.data['vehiculoDto'];
        this.obtenerVehiculoDesdeVehiculoDto(this.vehiculoDto);
        }

      }catch (errores){

        console.log('Se ha producido un error al transformar el Vehiculo' + errores);
      }
      }, (error) => {
      console.log('Error, no se ha podido recuperar el Vehiculo' + error);
      }
    );
  }


  obtenerVehiculoDesdeVehiculoDto(vehiculoDto: any): void{

    if ( vehiculoDto != null)
    {
      this.vehiculo.id = vehiculoDto.id;
      this.vehiculo.codigo = vehiculoDto.codigo;
      this.vehiculo.matricula = vehiculoDto.matricula;
      this.vehiculo.marca = vehiculoDto.marca;
      this.vehiculo.modelo = vehiculoDto.modelo;
      this.vehiculo.tipoVehiculo = vehiculoDto.tipoVehiculo;
      this.vehiculo.tipoCombustible = vehiculoDto.tipoCombustible;
      this.vehiculo.fechaMatriculacion = vehiculoDto.fechaMatriculacion;
    }
  }

  editarVehiculo(vehiculoId: number): void{
    console.log('Vehiculo CON ID: ' + vehiculoId);
    this.router.navigate(['vehiculos', 'editar-vehiculo', vehiculoId]);
  }

  borrarVehiculo(vehiculoId: number): void{

    console.log('Vehiculo CON ID: ' + vehiculoId);

    // Evitamos borrar accidentalmente un Vehiculo
    swal({
      title: 'Eliminar Vehiculo',
      text: '¿Desea eliminar definitivamente este Vehiculo?',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí',
      cancelButtonText: 'No'
    }).then( (resultado) => {
      // Si se pulsa en cancelar, no se continua
      if (!resultado.value) {
        return;
      }

      // Llamamos al servicio de Vehiculos para eliminar el Vehiculo
      this.vehiculoService.eliminarVehiculo(vehiculoId).toPromise().then( (accionRespuesta) => {

        // Si se ha eliminado correctamente
        if ( accionRespuesta.resultado ) {
        console.log('Se ha eliminado correctamente el Vehiculo');
        swal('Vehiculo elimninado', 'Se ha eliminado el Vehiculo correctamente', 'success').then(() => {
          this.router.navigate( ['vehiculos'] );
        });

        } else {
        console.log('Se ha producido un error al eliminar el Vehiculo');
        swal('Error', 'El Vehiculo no ha podido ser eliminado', 'error');
        }

      }, (errores) => {
        console.log('Se ha producido un error al eliminar el Vehiculo');
        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');
      } );
    } );

  }

  ngOnInit(): void {
  }

}
