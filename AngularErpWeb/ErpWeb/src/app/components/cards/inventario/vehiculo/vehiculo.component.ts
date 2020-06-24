import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { VehiculoService } from 'src/app/services/inventario/vehiculo.service';
import { Vehiculo } from 'src/app/model/entitys/vehiculo.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';



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
      this.vehiculo.fechaMatriculacion = vehiculoDto.fechaMatriculacion;
    }
  }

  ngOnInit(): void {
  }

}
