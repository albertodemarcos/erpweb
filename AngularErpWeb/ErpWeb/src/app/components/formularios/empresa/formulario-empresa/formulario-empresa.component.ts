import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { EmpresaService } from 'src/app/services/empresa/empresa.service';
import { Empresa } from 'src/app/model/entitys/empresa.model';

@Component({
  selector: 'app-formulario-empresa',
  templateUrl: './formulario-empresa.component.html',
  styleUrls: ['./formulario-empresa.component.css']
})
export class FormularioEmpresaComponent implements OnInit {

  public empresa: Empresa;

  constructor(private empresaService: EmpresaService, private router: Router) {

    this.empresa = new Empresa();

   }

  ngOnInit(): void {

  }

 // Metodos del formulario
 public crearEmpresaFormulario(): void {

  console.log('Estamos dentro del metodo crear formulario');

  this.empresaService.crearEmpresa(this.empresa).subscribe( accionRespuesta => {
    console.log('Esta registrado' + accionRespuesta.resultado);
    console.log('Datos que nos devuelve spring: ' + JSON.stringify(accionRespuesta));
    // Si el resultado es true, navegamos hasta la vista
    if (accionRespuesta.resultado && accionRespuesta.id !== null ) {
      this.router.navigate(['rrhh', 'empresa', accionRespuesta.id]);
    }
  });

 }



}
