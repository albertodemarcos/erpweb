import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ArticuloService } from 'src/app/services/inventario/articulo.service';
import { Articulo } from 'src/app/model/entitys/articulo.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import swal from 'sweetalert2';

declare var $: any;

@Component({
  selector: 'app-articulo',
  templateUrl: './articulo.component.html',
  styleUrls: ['./articulo.component.css']
})
export class ArticuloComponent implements OnInit {

  public articulo: Articulo;
  private articuloDto: any;
  private articuloId: number;
  public tiposImpuestos: string[];
  private respuestaGetArticulo: AccionRespuesta;
  public mapaIva: Map<string, string>;

  constructor(private articuloService: ArticuloService, private router: Router, private activateRouter: ActivatedRoute) {

    this.articuloId = 0;
    this.articulo = new Articulo();
    this.tiposImpuestos = ['IVA_GENERAL', 'IVA_REDUCIDO', 'IVA_SUPER_REDUCIDO'];
    this.mapaIva = new Map<string, string>();
    this.rellenaMapaIva();
    this.activateRouter.params.subscribe( params => {
      console.log('Entro al constructor' + params);
      // tslint:disable-next-line: no-string-literal
      this.articuloId = params['id'];
      this.getArticulo();
    } );
  }

  getArticulo(): void{

    this.articuloService.getArticulo(this.articuloId).toPromise().then( (articuloDto) => {
      try
      {
        console.log('Recuperamos el Articulo');

        this.respuestaGetArticulo = articuloDto;

        if ( this.respuestaGetArticulo.resultado )
        {
          // tslint:disable-next-line: no-string-literal
          this.articuloDto = this.respuestaGetArticulo.data['articuloDto'];
          this.obtenerArticuloDesdeArticuloDto(this.articuloDto);
          setTimeout(() => {
            $('#descripcionArticulo').after('' + this.articulo.descripcion);
          }, 200);
        }

      }catch (errores){

        console.log('Se ha producido un error al transformar el Articulo' + errores);
      }
      }, (error) => {
      console.log('Error, no se ha podido recuperar el Articulo' + error);
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

  public rellenaMapaIva(): void{
    this.mapaIva.set('IVA_GENERAL', 'GENERAL');
    this.mapaIva.set('IVA_REDUCIDO', 'REDUCIDO');
    this.mapaIva.set('IVA_SUPER_REDUCIDO', 'SUPER REDUCIDO');
  }

  editarArticulo(articuloId: number): void{
    console.log('Articulo CON ID: ' + articuloId);
    this.router.navigate(['catalago', 'editar-articulo', articuloId]);
  }

  borrarArticulo(articuloId: number): void{

    console.log('Articulo CON ID: ' + articuloId);

    // Evitamos borrar accidentalmente un Articulo
    swal({
      title: 'Eliminar Articulo',
      text: '¿Desea eliminar definitivamente este Articulo?',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí',
      cancelButtonText: 'No'
    }).then( (resultado) => {
      // Si se pulsa en cancelar, no se continua
      if (!resultado.value) {
        return;
      }

      // Llamamos al servicio de Articulos para eliminar el Articulo
      this.articuloService.eliminarArticulo(articuloId).toPromise().then( (accionRespuesta) => {

        // Si se ha eliminado correctamente
        if ( accionRespuesta.resultado ) {
        console.log('Se ha eliminado correctamente el Artículo');
        swal('Artículo eliminado', 'Se ha eliminado el Artículo correctamente', 'success').then(() => {
          this.router.navigate( ['catalago/articulos'] );
        });

        } else {
        console.log('Se ha producido un error al eliminar el Artículo');
        swal('Error', 'El Artículo no ha podido ser eliminado', 'error');
        }

      }, (errores) => {
        console.log('Se ha producido un error al eliminar el Artículo');
        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');
      } );
    } );

  }

  ngOnInit(): void {
  }

}
