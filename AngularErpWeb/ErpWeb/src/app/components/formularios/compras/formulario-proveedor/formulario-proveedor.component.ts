import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { ProveedorService } from 'src/app/services/compras/proveedor.service';
import { Proveedor } from 'src/app/model/entitys/proveedor.model';

@Component({
  selector: 'app-formulario-proveedor',
  templateUrl: './formulario-proveedor.component.html',
  styleUrls: ['./formulario-proveedor.component.css']
})
export class FormularioProveedorComponent implements OnInit {

  public proveedor: Proveedor;

  constructor(private proveedorService: ProveedorService, private router: Router) {

    this.proveedor = new Proveedor();

   }

  ngOnInit(): void {
  }

  // Metodos del formulario
  public crearProveedorFormulario(): void {

    console.log('Estamos dentro del metodo crear formulario');

    this.proveedorService.crearProveedor(this.proveedor).subscribe( accionRespuesta => {
      console.log('Esta registrado' + accionRespuesta.resultado);
      console.log('Datos que nos devuelve spring: ' + JSON.stringify(accionRespuesta));
      // Si el resultado es true, navegamos hasta la vista
      if (accionRespuesta.resultado && accionRespuesta.id !== null ) {
        this.router.navigate(['proveedor', 'proveedor', accionRespuesta.id]);
      }
    });

  }


}
