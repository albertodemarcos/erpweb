import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { VehiculoService } from 'src/app/services/inventario/vehiculo.service';
import { Vehiculo } from 'src/app/model/entitys/vehiculo.model';

@Component({
  selector: 'app-formulario-vehiculo',
  templateUrl: './formulario-vehiculo.component.html',
  styleUrls: ['./formulario-vehiculo.component.css']
})
export class FormularioVehiculoComponent implements OnInit {

  public vehiculo: Vehiculo;

  constructor(private vehiculoService: VehiculoService, private router: Router) {

    this.vehiculo = new Vehiculo();

   }

  ngOnInit(): void {
  }

  // Metodos del formulario
  public crearVehiculoFormulario(): void {

    console.log('Estamos dentro del metodo crear formulario');

    this.vehiculoService.crearVehiculo(this.vehiculo).subscribe( accionRespuesta => {
      console.log('Esta registrado' + accionRespuesta.resultado);
      console.log('Datos que nos devuelve spring: ' + JSON.stringify(accionRespuesta));
      // Si el resultado es true, navegamos hasta la vista
      if (accionRespuesta.resultado && accionRespuesta.id !== null ) {
        this.router.navigate(['clientes', 'cliente', accionRespuesta.id]);
      }
    });

  }

}
