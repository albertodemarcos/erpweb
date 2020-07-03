import { Component, OnInit, Input } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ProveedorService } from 'src/app/services/compras/proveedor.service';
import { Proveedor } from 'src/app/model/entitys/proveedor.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import swal from 'sweetalert2';

@Component({
  selector: 'app-proveedor',
  templateUrl: './proveedor.component.html',
  styleUrls: ['./proveedor.component.css']
})
export class ProveedorComponent implements OnInit {

  public proveedor: Proveedor;
  private proveedorDto: any;
  private proveedorId: number;
  private respuestaGetProveedor: AccionRespuesta;


  constructor(private proveedorService: ProveedorService, private router: Router, private activateRouter: ActivatedRoute) {

    this.proveedorId = 0;
    this.proveedor = new Proveedor();

    this.activateRouter.params.subscribe( params => {
      console.log('Entro al constructor' + params);
      // tslint:disable-next-line: no-string-literal
      this.proveedorId = params['id'];
      this.getProveedor();
    } );
  }

  getProveedor(): void{

    this.proveedorService.getProveedor(this.proveedorId).toPromise().then( (proveedorDto) => {
      try
      {
        console.log('Recuperamos el Proveedor');

        this.respuestaGetProveedor = proveedorDto;

        if ( this.respuestaGetProveedor.resultado )
        {
        console.log('Respuesta: ' +  JSON.stringify(this.respuestaGetProveedor.data) );
        console.log('ES: ' + typeof(this.respuestaGetProveedor.data));
        // tslint:disable-next-line: no-string-literal
        this.proveedorDto = this.respuestaGetProveedor.data['proveedorDto'];
        this.obtenerProveedorDesdeProveedorDto(this.proveedorDto);
        }

      }catch (errores){

        console.log('Se ha producido un error al transformar el Proveedor' + errores);
      }
      }, (error) => {
      console.log('Error, no se ha podido recuperar el Proveedor' + error);
      }
    );
  }

  obtenerProveedorDesdeProveedorDto(proveedorDto: any): void{

    if ( proveedorDto != null)
    {
      this.proveedor.id = proveedorDto.id;
      this.proveedor.codigo = proveedorDto.codigo;
      this.proveedor.nombre = proveedorDto.nombre;
      this.proveedor.nombreEmpresa = proveedorDto.nombreEmpresa;
      this.proveedor.telefono = proveedorDto.telefono;
      this.proveedor.tipoProveedor = proveedorDto.tipoProveedor;
    }
  }

  editarProveedor(proveedorId: number): void{
    console.log('Proveedor CON ID: ' + proveedorId);
    this.router.navigate(['proveedores', 'editar-proveedor', proveedorId]);
  }

  borrarProveedor(proveedorId: number): void{

    console.log('Proveedor CON ID: ' + proveedorId);

    // Evitamos borrar accidentalmente un Proveedor
    swal({
      title: 'Eliminar Proveedor',
      text: '¿Desea eliminar definitivamente este Proveedor?',
      type: 'warning',
      showCancelButton: true,
      confirmButtonText: 'Sí',
      cancelButtonText: 'No'
    }).then( (resultado) => {
      // Si se pulsa en cancelar, no se continua
      if (!resultado.value) {
        return;
      }

      // Llamamos al servicio de Proveedors para eliminar el Proveedor
      this.proveedorService.eliminarProveedor(proveedorId).toPromise().then( (accionRespuesta) => {

        // Si se ha eliminado correctamente
        if ( accionRespuesta.resultado ) {
        console.log('Se ha eliminado correctamente el Proveedor');
        swal('Proveedor elimninado', 'Se ha eliminado el Proveedor correctamente', 'success').then(() =>{
          this.router.navigate( ['proveedores'] );
        });

        } else {
        console.log('Se ha producido un error al eliminar el Proveedor');
        swal('Error', 'El Proveedor no ha podido ser eliminado', 'error');
        }

      }, (errores) => {
        console.log('Se ha producido un error al eliminar el Proveedor');
        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');
      } );
    } );

  }

  ngOnInit(): void {
  }

}
