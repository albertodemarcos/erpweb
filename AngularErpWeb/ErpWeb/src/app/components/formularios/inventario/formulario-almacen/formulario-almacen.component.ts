import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { AlmacenService } from 'src/app/services/inventario/almacen.service';
import { Almacen } from 'src/app/model/entitys/almacen.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import swal from 'sweetalert2';


@Component({
  selector: 'app-formulario-almacen',
  templateUrl: './formulario-almacen.component.html',
  styleUrls: ['./formulario-almacen.component.css']
})
export class FormularioAlmacenComponent implements OnInit {

  public almacen: Almacen;
  private almacenDto: any;
  private almacenId: number;
  public provincias: string[];
  public regiones: string[];
  private respuestaGetAlmacen: AccionRespuesta;
  public erroresFormulario: Map<string, object>;
  public titulo: string;
  public botonTitulo: string;

  constructor(private almacenService: AlmacenService, private router: Router, private activateRouter: ActivatedRoute) {

    this.almacen = new Almacen();
    this.erroresFormulario = new Map<string, object>();

    this.provincias = ['Álava', 'Albacete', 'Alicante', 'Almería', 'Asturias', 'Ávila', 'Badajoz', 'Barcelona', 'Burgos',
      'Cáceres', 'Cádiz', 'Cantabria', 'Castellón', 'Ciudad Real', 'Córdoba', 'La Coruña', 'Cuenca', 'Gerona', 'Granada',
      'Guadalajara', 'Guipúzcoa', 'Huelva', 'Huesca', 'Islas Baleares', 'Jaén', 'León', 'Lérida', 'Lugo', 'Madrid', 'Málaga',
      'Murcia', 'Navarra', 'Ourense', 'Palencia', 'Las Palmas', 'Pontevedra', 'La Rioja', 'Salamanca', 'Segovia', 'Sevilla',
      'Soria', 'Tarragona', 'Santa Cruz de Tenerife', 'Teruel', 'Toledo', 'Valencia', 'Valladolid', 'Vizcaya', 'Zamora', 'Zaragoza'];

    this.regiones = ['Andalucía', 'Aragón', 'Principado de Asturias', 'Islas Baleares', 'Islas Canarias', 'Cantabria', 'Castilla y León',
      'Castilla-La Mancha', 'Cataluña', 'Comunidad Valenciana', 'Extremadura', 'Galicia', 'Comunidad de Madrid', 'Región de Murcia',
      'Comunidad Foral de Navarra', 'País Vasco', 'La Rioja', 'Ciudad Autónoma de Ceuta', 'Ciudad Autónoma de Melilla' ];

    this.titulo = 'Nuevo almacén';
    this.botonTitulo = 'Crear almacén';

    this.activateRouter.params.subscribe( params => {
      console.log('Entro al constructor' + params);
      // tslint:disable-next-line: no-string-literal
      this.almacenId = params['id'];
      if (this.almacenId != null){
        this.getEditarAlmacen();
      }
    });
   }

  ngOnInit(): void {
  }

  // Metodos del formulario
  public crearAlmacenFormulario(): void {

    console.log('Estamos dentro del metodo crearClienteFormulario()');

    // Si tiene id, llamamos a crear, sino a editar
    if (this.almacen != null && this.almacen.id != null && this.almacen.id !== 0) {

      console.log('Vamos a editar el cliente con ID: ' + this.almacen.id);

      this.almacenService.actualizarAlmacen(this.almacen).subscribe( accionRespuesta => {

        this.respuestaCrearEditarAlmacen(accionRespuesta, true);

      }, (error => {

        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');

      }));

    } else {

      this.almacenService.crearAlmacen(this.almacen).subscribe( accionRespuesta => {

        console.log('Vamos a crear el cliente con codigo: ' + this.almacen.codigo);

        this.respuestaCrearEditarAlmacen(accionRespuesta, false);

      }, (error => {

        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');

      }));
    }
  }

  public getEditarAlmacen() {

    this.almacenService.getAlmacen(this.almacenId).toPromise().then( (accionRespuesta) => {
        try
        {
          this.respuestaGetAlmacen = accionRespuesta;

          if ( this.respuestaGetAlmacen.resultado )
          {
            // tslint:disable-next-line: no-string-literal
            this.almacenDto = this.respuestaGetAlmacen.data['almacenDto'];
            this.obtenerAlmacenDesdeAlmacenDto(this.almacenDto);
            this.titulo = 'Editar almacén';
            this.botonTitulo = 'Editar almacén';
          }

        }catch (errores){

          console.log('Se ha producido un error al transformar el cliente' + errores);
        }
      }, (error) => {
        console.log('Error, no se ha podido recuperar el cliente' + error);
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
      this.almacen.poblacion = almacenDto.poblacion;
      this.almacen.region = almacenDto.region;
      this.almacen.provincia = almacenDto.provincia;
      this.almacen.pais = almacenDto.pais;
    }
  }

  public respuestaCrearEditarAlmacen(accionRespuesta: AccionRespuesta, esEditarAlmacen: boolean): void {

    console.log('Esta registrado' + accionRespuesta.resultado);
    console.log('Datos que nos devuelve spring: ' + JSON.stringify(accionRespuesta));
    // Si el resultado es true, navegamos hasta la vista
    if (accionRespuesta.resultado && accionRespuesta.id !== null ) {

      this.router.navigate(['almacenes', 'almacen', accionRespuesta.id]);

      if (esEditarAlmacen != null && esEditarAlmacen ){

        swal('Almacén editado', 'Se ha editado el almacén correctamente', 'success');

       }else{

        swal('Nuevo almacén', 'Se ha creado el almacén correctamente', 'success');
       }

    }else{
      // Error
      if ( accionRespuesta != null && accionRespuesta.data != null && accionRespuesta.data !=  null )
      {
        this.erroresFormulario = accionRespuesta.data;
      }else
      {
        swal('Error', 'Se ha producido un error al guardar los datos del almacén', 'error');
      }
    }

  }



}
