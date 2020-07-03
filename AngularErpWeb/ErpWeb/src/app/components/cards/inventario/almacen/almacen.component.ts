import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AlmacenService } from 'src/app/services/inventario/almacen.service';
import { Almacen } from 'src/app/model/entitys/almacen.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import swal from 'sweetalert2';


@Component({
  selector: 'app-almacen',
  templateUrl: './almacen.component.html',
  styleUrls: ['./almacen.component.css']
})
export class AlmacenComponent implements OnInit {

  public almacen: Almacen;
  private almacenDto: any;
  private almacenId: number;
  private respuestaGetAlmacen: AccionRespuesta;

  constructor(private almacenService: AlmacenService, private router: Router, private activateRouter: ActivatedRoute) {

    this.almacenId = 0;
    this.almacen = new Almacen();

    this.activateRouter.params.subscribe( params => {
      console.log('Entro al constructor' + params);
      // tslint:disable-next-line: no-string-literal
      this.almacenId = params['id'];
      this.getAlmacen();
    } );
  }

  getAlmacen(): void{

    this.almacenService.getAlmacen(this.almacenId).toPromise().then( (almacenDto) => {
      try
      {
        console.log('Recuperamos el Almacen');

        this.respuestaGetAlmacen = almacenDto;

        if ( this.respuestaGetAlmacen.resultado )
        {
        console.log('Respuesta: ' +  JSON.stringify(this.respuestaGetAlmacen.data) );
        console.log('ES: ' + typeof(this.respuestaGetAlmacen.data));
        // tslint:disable-next-line: no-string-literal
        this.almacenDto = this.respuestaGetAlmacen.data['almacenDto'];
        this.obtenerAlmacenDesdeAlmacenDto(this.almacenDto);
        }

      }catch (errores){

        console.log('Se ha producido un error al transformar el Almacen' + errores);
      }
      }, (error) => {
      console.log('Error, no se ha podido recuperar el Almacen' + error);
      }
    );
  }


 obtenerAlmacenDesdeAlmacenDto(almacenDto: any): void{

    if ( almacenDto != null)
    {
      this.almacen.id = almacenDto.id;
      this.almacen.codigo = almacenDto.codigo;
      this.almacen.nombre = almacenDto.nombre;
      this.almacen.codigoPostal = almacenDto.codigoPostal;
      this.almacen.direccion = almacenDto.direccion;
      this.almacen.edificio = almacenDto.edificio;
      this.almacen.observaciones = almacenDto.observaciones;
      this.almacen.telefono = almacenDto.telefono;
      this.almacen.pais = almacenDto.pais;
      this.almacen.region = almacenDto.region;
      this.almacen.provincia = almacenDto.provincia;
      this.almacen.poblacion = almacenDto.poblacion;
    }
  }

  editarAlmacen(almacenId: number): void{
    console.log('Almacen CON ID: ' + almacenId);
    this.router.navigate(['almacenes', 'editar-almacen', almacenId]);
  }

  borrarAlmacen(almacenId: number): void{

    console.log('Almacen CON ID: ' + almacenId);

    // Evitamos borrar accidentalmente un Almacen
    swal({
      title: 'Eliminar Almacen',
      text: '¿Desea eliminar definitivamente este Almacen?',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí',
      cancelButtonText: 'No'
    }).then( (resultado) => {
      // Si se pulsa en cancelar, no se continua
      if (!resultado.value) {
        return;
      }

      // Llamamos al servicio de Almacens para eliminar el Almacen
      this.almacenService.eliminarAlmacen(almacenId).toPromise().then( (accionRespuesta) => {

        // Si se ha eliminado correctamente
        if ( accionRespuesta.resultado ) {
        console.log('Se ha eliminado correctamente el Almacen');
        swal('Almacen elimninado', 'Se ha eliminado el Almacen correctamente', 'success').then(() => {
          this.router.navigate( ['Almacens'] );
        });

        } else {
        console.log('Se ha producido un error al eliminar el Almacen');
        swal('Error', 'El Almacen no ha podido ser eliminado', 'error');
        }

      }, (errores) => {
        console.log('Se ha producido un error al eliminar el Almacen');
        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');
      } );
    } );

  }

  ngOnInit(): void {
  }

}
