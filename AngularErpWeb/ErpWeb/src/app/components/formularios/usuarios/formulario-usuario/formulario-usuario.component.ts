import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { UsuarioService } from 'src/app/services/usuarios/usuario.service';
import { Usuario } from 'src/app/model/entitys/usuario.model';

@Component({
  selector: 'app-formulario-usuario',
  templateUrl: './formulario-usuario.component.html',
  styleUrls: ['./formulario-usuario.component.css']
})
export class FormularioUsuarioComponent implements OnInit {

  public usuario: Usuario;

  constructor(private usuarioService: UsuarioService, private router: Router) {

    this.usuario = new Usuario();
  }

  // Metodos del formulario
  public crearUsuarioFormulario(): void {

    console.log('Estamos dentro del metodo crear formulario');

    this.usuarioService.crearUsuario(this.usuario).subscribe( accionRespuesta => {
      console.log('Esta registrado' + accionRespuesta.resultado);
      console.log('Datos que nos devuelve spring: ' + JSON.stringify(accionRespuesta));
      // Si el resultado es true, navegamos hasta la vista
      if (accionRespuesta.resultado && accionRespuesta.id !== null ) {
        this.router.navigate(['usuarios', 'usuario', accionRespuesta.id]);
      }
    });

  }

  ngOnInit(): void {
  }

}

