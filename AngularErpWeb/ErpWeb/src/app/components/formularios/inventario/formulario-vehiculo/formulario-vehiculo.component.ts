import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { VehiculoService } from 'src/app/services/inventario/vehiculo.service';
import { Vehiculo } from 'src/app/model/entitys/vehiculo.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import swal from 'sweetalert2';

@Component({
  selector: 'app-formulario-vehiculo',
  templateUrl: './formulario-vehiculo.component.html',
  styleUrls: ['./formulario-vehiculo.component.css']
})
export class FormularioVehiculoComponent implements OnInit {

  public vehiculo: Vehiculo;
  private vehiculoId: number;
  public tiposCombustible: string[];
  public tiposVehiculos: string[];
  private vehiculoDto: any;
  private respuestaGetVehiculo: AccionRespuesta;

  constructor(private vehiculoService: VehiculoService, private router: Router, private activateRouter: ActivatedRoute) {

    this.vehiculo = new Vehiculo();
    this.tiposVehiculos = ['COCHE', 'FURGONETA', 'CAMION', 'MOTOCICLETA', 'CICLOMOTOR', 'MIXTO'];
    this.tiposCombustible = ['DIESEL', 'GASOLINA', 'HIBRIDO', 'ELECTRICO', 'GAS'];
    this.activateRouter.params.subscribe( params => {
      console.log('Entro al constructor' + params);
      // tslint:disable-next-line: no-string-literal
      this.vehiculoId = params['id'];
      if (this.vehiculoId != null){
        this.getEditarVehiculo();
      }
    });
   }

  ngOnInit(): void {
  }

  // Metodos del formulario
  public crearVehiculoFormulario(): void {

    console.log('Estamos dentro del metodo crearVehiculoFormulario()');

    // Si tiene id, llamamos a crear, sino a editar
    if (this.vehiculo != null && this.vehiculo.id != null && this.vehiculo.id !== 0) {

      console.log('Vamos a editar el vehiculo con ID: ' + this.vehiculo.id);

      this.vehiculoService.actualizarVehiculo(this.vehiculo).subscribe( accionRespuesta => {

        this.respuestaCrearEditarVehiculo(accionRespuesta, true);

      }, (error => {

        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');

      }));

    } else {

      this.vehiculoService.crearVehiculo(this.vehiculo).subscribe( accionRespuesta => {

        console.log('Vamos a crear el vehiculo con codigo: ' + this.vehiculo.codigo);

        this.respuestaCrearEditarVehiculo(accionRespuesta, false);

      }, (error => {

        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');

      }));
    }
  }

  getEditarVehiculo() {

    this.vehiculoService.getVehiculo(this.vehiculoId).toPromise().then( (accionRespuesta) => {
        try
        {
          console.log('Recuperamos el vehiculo');

          this.respuestaGetVehiculo = accionRespuesta;

          if ( this.respuestaGetVehiculo.resultado )
          {
            console.log('Respuesta: ' +  JSON.stringify(this.respuestaGetVehiculo.data) );
            console.log('ES: ' + typeof(this.respuestaGetVehiculo.data));
            // tslint:disable-next-line: no-string-literal
            this.vehiculoDto = this.respuestaGetVehiculo.data['vehiculoDto'];
            this.obtenerVehiculoDesdeVehiculoDto(this.vehiculoDto);
          }

        }catch (errores){

          console.log('Se ha producido un error al transformar el vehiculo' + errores);
        }
      }, (error) => {
        console.log('Error, no se ha podido recuperar el vehiculo' + error);
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
      this.vehiculo.fechaMatriculacion = this.limpiarFecha(vehiculoDto.fechaMatriculacion);
    }
  }

  respuestaCrearEditarVehiculo(accionRespuesta: AccionRespuesta, esEditarVehiculo: boolean): void {

    console.log('Esta registrado' + accionRespuesta.resultado);
    console.log('Datos que nos devuelve spring: ' + JSON.stringify(accionRespuesta));
    // Si el resultado es true, navegamos hasta la vista
    if (accionRespuesta.resultado && accionRespuesta.id !== null ) {

      this.router.navigate(['vehiculos', 'vehiculo', accionRespuesta.id]);

      if (esEditarVehiculo != null && esEditarVehiculo ){

        swal('vehiculo editado', 'Se ha editado el vehiculo correctamente', 'success');

       }else{

        swal('Nuevo vehiculo', 'Se ha creado el vehiculo correctamente', 'success');
       }

    }else{

      swal('Nuevo vehiculo', 'Se ha producido un error al crear el vehiculo', 'success');
    }
  }

  limpiarFecha(fechaStr: string): Date{

    if (fechaStr != null && fechaStr.trim() !== ''){
      try {
        const fechaLimpia: Date = new Date(fechaStr);
        return fechaLimpia;
      } catch (error) {
        console.log('Error al convertir la fecha' + error);
      }
    }
    return new Date();
  }

}
