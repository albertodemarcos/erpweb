import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AutenticacionService } from 'src/app/services/autenticacion/autenticacion.service';
import { AccionRespuesta } from '../../model/utiles/accion-respuesta.model';
import { Usuario } from '../../model/entitys/usuario.model';
import swal from 'sweetalert2';
import { UsuarioLoginService } from 'src/app/services/autenticacion/usuario-login.service';
import { EncriptadorService } from 'src/app/services/autenticacion/encriptador.service';


@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public titulo: string;
  public invalidLogin: boolean;
  public usuario: Usuario;
  private usuarioDto: any;
  private respuestaGetUsuario: AccionRespuesta;


  constructor(private usuarioLoginService: UsuarioLoginService, private encriptadorService: EncriptadorService, private router: Router) {
    this.titulo = 'Bienvenido';
    this.invalidLogin = false;
    this.usuario = new Usuario();
  }

  getLoginUsuario(): void {

    console.log('Entramos a validar el usuario: ' + JSON.stringify(this.usuario) );

    // Comprobamos que el usuario no deja nigun campo sin rellenar
    if ( !this.comprobarLoginCorrecto() )
    {
      console.log('ERROR');
      return;
    }

    this.usuario.password = this.encriptadorService.encriptarTextoParaEnvioHtttp(this.usuario.password);

    console.log('Codificado: ' + this.usuario.password);

    this.usuarioLoginService.autenticarUsuario(this.usuario).toPromise().then(
      ( accionRespuesta ) => {

        console.log('Obtenemos: ' + JSON.stringify(accionRespuesta));

        if ( !accionRespuesta.resultado )
        {
          this.usuario.username = null;
          this.usuario.password = null;
          swal('Error', 'Usuario no esta registrado. Comprueba si el nombre de usuario y contraseña son correctos', 'error');
          return;
        }

        // tslint:disable-next-line: no-string-literal
        this.usuarioDto = accionRespuesta.data['usuarioDto'];

        sessionStorage.setItem('username', this.usuarioDto.username);

        // sessionStorage.setItem('password', this.usuarioDto.password);

        this.router.navigate(['inicio']);

        this.invalidLogin = false;

      }, (errores) => {

        this.invalidLogin = true;

        swal('Error', 'Usuario no esta registrado. Comprueba si el nombre de usuario y contraseña son correctos', 'error');

      }
    );

  }

  /* METODOS AUXILIARES*/

  comprobarLoginCorrecto(): boolean {

    // Error el nombre de usuario no se ha introducido correctamente
    if ( (this.usuario.username == null || this.usuario.username.trim() === '')
          && (this.usuario.password == null || this.usuario.password.trim() === '') )
    {
      // Error, falta todo
      swal('Error', 'Introduce el nombre de usuario y contraseña', 'error');
      return false;

    } else if (this.usuario.username == null || this.usuario.username.trim() === '')
    {
      // Error, falta el usuario
      swal('Error', 'Introduce el nombre de usuario', 'error');
      return false;

    } else if (this.usuario.password == null || this.usuario.password.trim() === '')
    {
      // Error falta la contraseña
      swal('Error', 'Introduce la contraseña', 'error');
      return false;
    }

    return true;
  }




  ngOnInit(): void {
  }

}
