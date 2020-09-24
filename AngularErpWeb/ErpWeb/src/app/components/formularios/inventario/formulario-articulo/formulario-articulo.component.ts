import { Component, OnInit, AfterViewInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
// Servicio
import { ArticuloService } from 'src/app/services/inventario/articulo.service';
import { AlmacenService } from 'src/app/services/inventario/almacen.service';
// Model
import { Articulo } from 'src/app/model/entitys/articulo.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
// Swal
import swal from 'sweetalert2';
// CKEDITOR
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';

declare var $: any;

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
  public almacenesForm: Map<number, string>;
  public titulo: string;
  public botonTitulo: string;
  public classicEditor: ClassicEditor;
  public configurationClassicEditor: {};

  constructor(private articuloService: ArticuloService,
              private almacenService: AlmacenService,
              private router: Router,
              private activateRouter: ActivatedRoute) {

    this.articulo = new Articulo();
    this.erroresFormulario = new Map<string, object>();
    this.tiposImpuestos = ['IVA_GENERAL', 'IVA_REDUCIDO', 'IVA_SUPER_REDUCIDO'];
    this.mapaIva = new Map<string, string>();
    this.almacenesForm = new Map<number, string>();
    this.rellenaMapaIva();
    this.titulo = 'Nuevo artículo';
    this.botonTitulo = 'Crear artículo';
    // Routeo
    this.activateRouter.params.subscribe( params => {
      console.log('Entro al constructor' + params);
      // tslint:disable-next-line: no-string-literal
      this.articuloId = params['id'];
      if (this.articuloId != null){
        this.getEditarArticulo();
      }
    });

    this.classicEditor = ClassicEditor;

    this.configurationClassicEditor = {
      toolbar: [ 'heading', '|', 'bold', 'italic', 'alignment', 'link', 'bulletedList', 'numberedList', 'imageUpload', 'blockQuote', 'undo', 'redo' ],
      language: 'es'
    };

   }

  ngOnInit(): void {
    // Buscamos los almacenes disponibles para el articulo
    this.rellenarSelectorAlmacenes();
  }

  // Metodos del formulario
  public crearArticuloFormulario(): void {

    console.log('Estamos dentro del metodo crearArticuloFormulario()');

    // Si tiene id, llamamos a crear, sino a editar
    if (this.articulo != null && this.articulo.id != null && this.articulo.id !== 0) {

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

  public getEditarArticulo() {

    this.articuloService.getArticulo(this.articuloId).toPromise().then( (accionRespuesta) => {
        try
        {
          this.respuestaGetArticulo = accionRespuesta;

          if ( this.respuestaGetArticulo.resultado )
          {
            // tslint:disable-next-line: no-string-literal
            this.articuloDto = this.respuestaGetArticulo.data['articuloDto'];
            this.obtenerArticuloDesdeArticuloDto(this.articuloDto);
            this.titulo = 'Editar artículo';
            this.botonTitulo = 'Editar artículo';
          }

        }catch (errores){

          console.log('Se ha producido un error al transformar el cliente' + errores);
        }
      }, (error) => {
        console.log('Error, no se ha podido recuperar el cliente' + error);
      }
    );
  }

  private obtenerArticuloDesdeArticuloDto(articuloDto: any): void{

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

  private respuestaCrearEditarArticulo(accionRespuesta: AccionRespuesta, esEditarArticulo: boolean): void {

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

  public rellenaMapaIva(): void{
    this.mapaIva.set('IVA_GENERAL', 'GENERAL (21%)');
    this.mapaIva.set('IVA_REDUCIDO', 'REDUCIDO (10%)');
    this.mapaIva.set('IVA_SUPER_REDUCIDO', 'SUPER REDUCIDO (4%)');
  }

  public calcularImporteTotal(): void{
    // calculamos el importe total en base a los impuestos
    if (this.articulo.baseImponible != null && this.comprobarImpuesto() )
    {
      switch (this.articulo.impuesto){

        case 'IVA_GENERAL':
          this.articulo.importeTotal = parseFloat((this.articulo.baseImponible * 1.21).toFixed(3));
          break;

        case 'IVA_REDUCIDO':
          this.articulo.importeTotal = parseFloat((this.articulo.baseImponible * 1.1).toFixed(3));
          break;

        case 'IVA_SUPER_REDUCIDO':
          this.articulo.importeTotal = parseFloat((this.articulo.baseImponible * 1.04).toFixed(3));
          break;

        default:
          break;
      }
    }
  }

  private comprobarImpuesto(): boolean{
    // Si el impuesto no esta vacio ni nulo
    return (this.articulo.impuesto != null && this.articulo.impuesto !== 'undefined' && this.articulo.impuesto.trim() !== '');
  }

  public rellenarSelectorAlmacenes(): void{
    // Realizamos la llamada al servidor para traer los almacenes y llevarlos al formulario
    this.almacenService.getAlmacenes().then(
      (almacenes) => {
        try {
          // Introducimos los datos
          almacenes.forEach(almacen => {
            const almacenId = almacen.id;
            const codigo = almacen.codigo;
            const nombre = almacen.nombre;
            let nombreAlmacen = '' + nombre;
            if ( codigo != null && codigo !== 'undefined' && codigo.trim() !== '')
            {
              nombreAlmacen = codigo + ' - ' + nombre;
            }

            this.almacenesForm.set(almacenId, nombreAlmacen);
          });

        } catch (errores){
          console.error('Se ha producido un error al convertir la infomracion del servidor' + errores);
        }
      }, (errores) => {
        console.log('Error, no se ha obtenido la informacion');
      }
    );


  }


}
