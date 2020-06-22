import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { EmpleadoService } from 'src/app/services/empresa/empleado.service';
import { Empleado } from 'src/app/model/entitys/empleado.model';

@Component({
  selector: 'app-formulario-empleado',
  templateUrl: './formulario-empleado.component.html',
  styleUrls: ['./formulario-empleado.component.css']
})
export class FormularioEmpleadoComponent implements OnInit {

  public empleado: Empleado;


  constructor(private empleadoService: EmpleadoService, private router: Router) {

    this.empleado = new Empleado();

  }

  ngOnInit(): void {
  }


   // Metodos del formulario

   public crearEmpleadoFormulario(): void {

    console.log('Estamos dentro del metodo crear formulario');

    this.empleadoService.crearEmpleado(this.empleado).subscribe( accionRespuesta => {
      console.log('Esta registrado' + accionRespuesta.resultado);
      console.log('Datos que nos devuelve spring: ' + JSON.stringify(accionRespuesta));
      // Si el resultado es true, navegamos hasta la vista
      if (accionRespuesta.resultado && accionRespuesta.id !== null ) {
        this.router.navigate(['rrhh', 'empleado', accionRespuesta.id]);
      }
    });

  }

}
