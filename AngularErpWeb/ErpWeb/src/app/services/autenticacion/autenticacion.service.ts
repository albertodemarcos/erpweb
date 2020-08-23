import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { UsuarioLoginService } from './usuario-login.service';

declare var $: any;

@Injectable({
  providedIn: 'root'
})
export class AutenticacionService implements CanActivate {

  constructor(private router: Router,  private usuarioLoginService: UsuarioLoginService) { }


  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {

    // Comprobamos si el usuario esta logeado
    if (this.usuarioLoginService.esUsuarioLogado())
    {
      return true;
    }

    // Ocultamos perfil y mostramos login
    $('#perfilLogado').hide();
    $('#perfilNoLogado').show();

    // Si no esta logeado, se manda al login
    this.router.navigate(['login']);

    return false;
  }
}

