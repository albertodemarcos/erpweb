import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { UsuarioService } from 'src/app/services/usuarios/usuario.service';
import { Usuario } from 'src/app/model/entitys/usuario.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css']
})
export class UsuarioComponent implements OnInit {

  public usuario: Usuario;
  private usuarioDto: any;
  private usuarioId: number;
  private respuestaGetUsuario: AccionRespuesta;

  constructor(private usuarioService: UsuarioService, private router: Router, private activateRouter: ActivatedRoute) {

    this.usuarioId = 0;
    this.usuario = new Usuario();

    this.activateRouter.params.subscribe( params => {
      console.log('Entro al constructor' + params);
      // tslint:disable-next-line: no-string-literal
      this.usuarioId = params['id'];
      this.getUsuario();
    } );
  }

  getUsuario(): void{

    this.usuarioService.getUsuario(this.usuarioId).toPromise().then( (usuarioDto) => {
      try
      {
        console.log('Recuperamos el Usuario');

        this.respuestaGetUsuario = usuarioDto;

        if ( this.respuestaGetUsuario.resultado )
        {
        console.log('Respuesta: ' +  JSON.stringify(this.respuestaGetUsuario.data) );
        console.log('ES: ' + typeof(this.respuestaGetUsuario.data));
        // tslint:disable-next-line: no-string-literal
        this.usuarioDto = this.respuestaGetUsuario.data['UsuarioDto'];
        this.obtenerUsuarioDesdeUsuarioDto(this.usuarioDto);
        }

      }catch (errores){

        console.log('Se ha producido un error al transformar el Usuario' + errores);
      }
      }, (error) => {
      console.log('Error, no se ha podido recuperar el Usuario' + error);
      }
    );
  }

  obtenerUsuarioDesdeUsuarioDto(usuarioDto: any): void{

    if ( usuarioDto != null)
      {
        this.usuario.id = usuarioDto.id;
        this.usuario.codigo = usuarioDto.codigo;
        this.usuario.name = usuarioDto.name;
        this.usuario.password = usuarioDto.password;
      }
  }

  ngOnInit(): void {
  }

}




