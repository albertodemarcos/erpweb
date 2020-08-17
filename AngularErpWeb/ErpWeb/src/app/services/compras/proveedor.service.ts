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
  private httpHeaders: HttpHeaders;
  private urlCrearProveedor: string;
  private urlListadoProveedores: string;
  private urlGetProveedor: string;
  private urlEditarProveedor: string;
  private urlEliminarProveedor: string;

  constructor(private httpClient: HttpClient) {
    this.urlGeneral = 'http://localhost:8080';
    this.urlCrearProveedor = '/proveedores/crearProveedor';
    this.urlListadoProveedores = '/proveedores/listado.json';
    this.urlGetProveedor = '/proveedores/proveedor/';
    this.urlEditarProveedor = '/proveedores/editarProveedor/';
    this.urlEliminarProveedor = '/proveedores/eliminarProveedor/';
    this.httpHeaders = new HttpHeaders({
      'Content-Type': 'application/json; charset=utf-8',
       Authorization: 'Bearer ' + sessionStorage.getItem('token'),
       Accept: '*/*',
    });
  }

  // METODOS GENERALES
  public getProveedor(id: number): Observable<AccionRespuesta> {
    console.log('METODO obtener');
    const urlGet = this.urlGeneral + this.urlGetProveedor + id;
    return this.httpClient.get<AccionRespuesta>(urlGet);
  }

  public getProveedorEditar(proveedorId: number): Observable<AccionRespuesta>{
    console.log('METODO actualizar');
    const urlGet = this.urlGeneral + this.urlEditarProveedor + proveedorId;
    return this.httpClient.get<AccionRespuesta>(urlGet);
  }

  public crearProveedor(proveedor: Proveedor): Observable<AccionRespuesta>{
    console.log('METODO crear');
    const urlPost = this.urlGeneral + this.urlCrearProveedor;
    return this.httpClient.post<AccionRespuesta>(urlPost, proveedor, {headers: this.httpHeaders});
  }

  public actualizarProveedor(proveedor: Proveedor): Observable<AccionRespuesta>{
    console.log('METODO actualizar');
    const urlPost = this.urlGeneral + this.urlEditarProveedor;
    return this.httpClient.post<AccionRespuesta>(urlPost, proveedor, {headers: this.httpHeaders});
  }

  public eliminarProveedor(proveedorId: number): Observable<AccionRespuesta>{
    console.log('METODO GET');
    const urlGet = this.urlGeneral + this.urlEliminarProveedor + proveedorId;
    return this.httpClient.get<AccionRespuesta>(urlGet);
  }

  public getProveedores(): Promise<Proveedor[]> {
    console.log('METODO listado');
    const urlPost = this.urlGeneral + this.urlListadoProveedores;
    return this.httpClient.get<Proveedor[]>(urlPost).pipe(map(response => response as Proveedor[])).toPromise();
  }
}
