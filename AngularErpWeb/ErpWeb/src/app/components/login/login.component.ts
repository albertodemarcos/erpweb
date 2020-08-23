import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
// Autenticacion
import { UsuarioLoginService } from 'src/app/services/autenticacion/usuario-login.service';
import { EncriptadorService } from 'src/app/services/autenticacion/encriptador.service';
import { AutenticacionRequest } from 'src/app/model/entitys/autenticacion-request.model';
// Otros
import { Usuario } from '../../model/entitys/usuario.model';
import swal from 'sweetalert2';


declare var $: any;

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  public titulo: string;
  public invalidLogin: boolean;
  public usuario: Usuario;
  private respuesta: any;
  private token: any;

  constructor(private usuarioLoginService: UsuarioLoginService,
              private encriptadorService: EncriptadorService,
              private router: Router) {
    this.titulo = 'Bienvenido';
    this.invalidLogin = false;
    this.usuario = new Usuario();
  }

  public getLoginUsuario(): void {

    console.log('Entramos a validar el usuario: ' + JSON.stringify(this.usuario) );

    // Comprobamos que el usuario no deja nigun campo sin rellenar
    if ( !this.comprobarLoginCorrecto() )
    {
      console.log('ERROR');
      return;
    }

    const password = this.encriptadorService.encriptarTextoParaEnvioHtttp(this.usuario.password);
    const usuario = new AutenticacionRequest(this.usuario.username, password);

    this.usuarioLoginService.autenticarUsuario(usuario).toPromise().then(
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
        this.respuesta = accionRespuesta.data['autenticacionResponse'];

        // tslint:disable-next-line: no-string-literal
        this.token = this.respuesta['jwt'].split(['.']['1']);

        sessionStorage.setItem('username', this.usuario.username);

        sessionStorage.setItem('token', this.token);

        this.router.navigate(['inicio']);

        this.invalidLogin = false;

        // Usuario logado o no
        if (!this.invalidLogin)
        {
          console.log('Mostramos el navbar');
          $('#perfilLogado').show();
          $('#perfilNoLogado').hide();
        }
        else
        {
          console.log('Ocultamos el navbar');
          $('#perfilLogado').hide();
          $('#perfilNoLogado').show();
        }

      }, (errores) => {

        this.invalidLogin = true;

        swal('Error', 'Usuario no esta registrado. Comprueba si el nombre de usuario y contraseña son correctos', 'error');

      }
    );

  }

  /* METODOS AUXILIARES*/

  private comprobarLoginCorrecto(): boolean {

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
