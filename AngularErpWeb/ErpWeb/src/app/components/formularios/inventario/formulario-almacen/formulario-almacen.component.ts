import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { AlmacenService } from 'src/app/services/inventario/almacen.service';
import { Almacen } from 'src/app/model/entitys/almacen.model';

@Component({
  selector: 'app-formulario-almacen',
  templateUrl: './formulario-almacen.component.html',
  styleUrls: ['./formulario-almacen.component.css']
})
export class FormularioAlmacenComponent implements OnInit {

  public almacen: Almacen;

  constructor(private almacenService: AlmacenService, private router: Router) {

    this.almacen = new Almacen();

   }

  ngOnInit(): void {
  }

  // Metodos del formulario
  public crearAlmacenFormulario(): void {

    console.log('Estamos dentro del metodo crear formulario');

    this.almacenService.crearAlmacen(this.almacen).subscribe( accionRespuesta => {
      console.log('Esta registrado' + accionRespuesta.resultado);
      console.log('Datos que nos devuelve spring: ' + JSON.stringify(accionRespuesta));
      // Si el resultado es true, navegamos hasta la vista
      if (accionRespuesta.resultado && accionRespuesta.id !== null ) {
        this.router.navigate(['clientes', 'cliente', accionRespuesta.id]);
      }
    });

  }



}
