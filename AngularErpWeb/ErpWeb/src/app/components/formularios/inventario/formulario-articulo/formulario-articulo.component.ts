import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { ArticuloService } from 'src/app/services/inventario/articulo.service';
import { Articulo } from 'src/app/model/entitys/articulo.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import swal from 'sweetalert2';


@Component({
  selector: 'app-formulario-articulo',
  templateUrl: './formulario-articulo.component.html',
  styleUrls: ['./formulario-articulo.component.css']
})
export class FormularioArticuloComponent implements OnInit {

  public articulo: Articulo;
  private articuloId: number;
  private articuloDto: any;
  public tiposImpuestos: string[];
  private respuestaGetArticulo: AccionRespuesta;
  public erroresFormulario: Map<string, object>;
  public mapaIva: Map<string, string>;

  constructor(private articuloService: ArticuloService, private router: Router, private activateRouter: ActivatedRoute) {

    this.articulo = new Articulo();
    this.erroresFormulario = new Map<string, object>();
    this.tiposImpuestos = ['IVA_GENERAL', 'IVA_REDUCIDO', 'IVA_SUPER_REDUCIDO'];
    this.mapaIva = new Map<string, string>();
    this.rellenaMapaIva();
    this.activateRouter.params.subscribe( params => {
      console.log('Entro al constructor' + params);
      // tslint:disable-next-line: no-string-literal
      this.articuloId = params['id'];
      if (this.articuloId != null){
        this.getEditarArticulo();
      }
    });
   }

  ngOnInit(): void {
  }

  // Metodos del formulario
  public crearArticuloFormulario(): void {

    console.log('Estamos dentro del metodo crearArticuloFormulario()');

    // Si tiene id, llamamos a crear, sino a editar
    if (this.articulo != null && this.articulo.id != null && this.articulo.id !== 0) {

      console.log('Vamos a editar el cliente con ID: ' + this.articulo.id);

      this.articuloService.actualizarArticulo(this.articulo).subscribe( accionRespuesta => {

        this.respuestaCrearEditarArticulo(accionRespuesta, true);

      }, (error => {

        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');

      }));

    } else {

      this.articuloService.crearArticulo(this.articulo).subscribe( accionRespuesta => {

        console.log('Vamos a crear el cliente con codigo: ' + this.articulo.codigo);

        this.respuestaCrearEditarArticulo(accionRespuesta, false);

      }, (error => {

        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');

      }));
    }

  }

  getEditarArticulo() {

    this.articuloService.getArticulo(this.articuloId).toPromise().then( (accionRespuesta) => {
        try
        {
          console.log('Recuperamos el cliente');

          this.respuestaGetArticulo = accionRespuesta;

          if ( this.respuestaGetArticulo.resultado )
          {
            console.log('Respuesta: ' +  JSON.stringify(this.respuestaGetArticulo.data) );
            console.log('ES: ' + typeof(this.respuestaGetArticulo.data));
            // tslint:disable-next-line: no-string-literal
            this.articuloDto = this.respuestaGetArticulo.data['articuloDto'];
            this.obtenerArticuloDesdeArticuloDto(this.articuloDto);
          }

        }catch (errores){

          console.log('Se ha producido un error al transformar el cliente' + errores);
        }
      }, (error) => {
        console.log('Error, no se ha podido recuperar el cliente' + error);
      }
    );
  }

  obtenerArticuloDesdeArticuloDto(articuloDto: any): void{

    if ( articuloDto != null)
    {
      this.articulo.id = articuloDto.id;
      this.articulo.codigo = articuloDto.codigo;
      this.articulo.nombre = articuloDto.nombre;
      this.articulo.descripcion = articuloDto.descripcion;
      this.articulo.baseImponible = articuloDto.baseImponible;
      this.articulo.impuesto = articuloDto.impuesto;
      this.articulo.importeTotal = articuloDto.importeTotal;
    }
  }

  respuestaCrearEditarArticulo(accionRespuesta: AccionRespuesta, esEditarArticulo: boolean): void {

    console.log('Esta registrado' + accionRespuesta.resultado);
    console.log('Datos que nos devuelve spring: ' + JSON.stringify(accionRespuesta));
    // Si el resultado es true, navegamos hasta la vista
    if (accionRespuesta.resultado && accionRespuesta.id !== null ) {

      this.router.navigate(['catalago', 'articulo', accionRespuesta.id]);

      if (esEditarArticulo != null && esEditarArticulo ){

        swal('articulo editado', 'Se ha editado el articulo correctamente', 'success');

       }else{

        swal('Nuevo articulo', 'Se ha creado el articulo correctamente', 'success');

       }

    }else{

      if ( accionRespuesta != null && accionRespuesta.data != null && accionRespuesta.data !=  null )
      {
        this.erroresFormulario = accionRespuesta.data;
      }else
      {
        swal('Error', 'Se ha producido un error al guardar los datos del artículo', 'error');
      }
    }

  }

  rellenaMapaIva(): void{
    this.mapaIva.set('IVA_GENERAL', 'GENERAL');
    this.mapaIva.set('IVA_REDUCIDO', 'REDUCIDO');
    this.mapaIva.set('IVA_SUPER_REDUCIDO', 'SUPER REDUCIDO');
  }

}
