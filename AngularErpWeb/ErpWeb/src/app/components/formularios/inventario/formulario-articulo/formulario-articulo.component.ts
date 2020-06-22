import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { ArticuloService } from 'src/app/services/inventario/articulo.service';
import { Articulo } from 'src/app/model/entitys/articulo.model';

@Component({
  selector: 'app-formulario-articulo',
  templateUrl: './formulario-articulo.component.html',
  styleUrls: ['./formulario-articulo.component.css']
})
export class FormularioArticuloComponent implements OnInit {

  public articulo: Articulo;

  constructor(private articuloService: ArticuloService, private router: Router) {

    this.articulo = new Articulo();

   }

  ngOnInit(): void {
  }

  // Metodos del formulario
  public crearArticuloFormulario(): void {

    console.log('Estamos dentro del metodo crear formulario');

    this.articuloService.crearArticulo(this.articulo).subscribe( accionRespuesta => {
      console.log('Esta registrado' + accionRespuesta.resultado);
      console.log('Datos que nos devuelve spring: ' + JSON.stringify(accionRespuesta));
      // Si el resultado es true, navegamos hasta la vista
      if (accionRespuesta.resultado && accionRespuesta.id !== null ) {
        this.router.navigate(['clientes', 'cliente', accionRespuesta.id]);
      }
    });

  }

}
