import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { UsuarioLoginService } from './usuario-login.service';

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

    // Si no esta logeado, se manda al login
    this.router.navigate(['login']);

    return false;
  }
}
