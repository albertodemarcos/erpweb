import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ArticuloService } from 'src/app/services/inventario/articulo.service';
import { Articulo } from 'src/app/model/entitys/articulo.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';



@Component({
  selector: 'app-articulo',
  templateUrl: './articulo.component.html',
  styleUrls: ['./articulo.component.css']
})
export class ArticuloComponent implements OnInit {

  public articulo: Articulo;
  private articuloDto: any;
  private articuloId: number;
  private respuestaGetArticulo: AccionRespuesta;

  constructor(private articuloService: ArticuloService, private router: Router, private activateRouter: ActivatedRoute) {

    this.articuloId = 0;
    this.articulo = new Articulo();

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
        console.log('Respuesta: ' +  JSON.stringify(this.respuestaGetArticulo.data) );
        console.log('ES: ' + typeof(this.respuestaGetArticulo.data));
        // tslint:disable-next-line: no-string-literal
        this.articuloDto = this.respuestaGetArticulo.data['articuloDto'];
        this.obtenerArticuloDesdeArticuloDto(this.articuloDto);
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

  ngOnInit(): void {
  }

}
