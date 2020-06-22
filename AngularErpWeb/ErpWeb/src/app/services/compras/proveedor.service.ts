import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import { Proveedor } from 'src/app/model/entitys/proveedor.model';

@Injectable({
  providedIn: 'root'
})
export class ProveedorService {

  private urlGeneral: string;
  private httpHeaders = new HttpHeaders({'Content-Type': 'application/json; charset=utf-8'});
  private urlCrearProveedor: string;
  private urlListadoProveedores: string;

  constructor(private httpClient: HttpClient) {
    this.urlGeneral = 'http://localhost:8080';
    this.urlCrearProveedor = '/proveedores/crearProveedor';
    this.urlListadoProveedores = '/proveedores/listado.json';
  }

  // METODOS GENERALES

  public getProveedor(){
    console.log('METODO obtener');
  }

  public crearProveedor(proveedor: Proveedor): Observable<AccionRespuesta>{
    console.log('METODO crear');
    const urlPost = this.urlGeneral + this.urlCrearProveedor;
    return this.httpClient.post<AccionRespuesta>(urlPost, proveedor, {headers: this.httpHeaders});
  }

  public actualizarProveedor(){
    console.log('METODO actualizar');
  }

  public eliminarProveedor(){
    console.log('METODO GET');
  }

  public getProveedores(): Promise<Proveedor[]> {
    console.log('METODO listado');
    const urlPost = this.urlGeneral + this.urlListadoProveedores;
    return this.httpClient.get<Proveedor[]>(urlPost).pipe(map(response => response as Proveedor[])).toPromise();
  }
}
