import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';
import { ProveedorService } from 'src/app/services/compras/proveedor.service';
import { Proveedor } from 'src/app/model/entitys/proveedor.model';
import { AccionRespuesta } from 'src/app/model/utiles/accion-respuesta.model';
import swal from 'sweetalert2';


@Component({
  selector: 'app-formulario-proveedor',
  templateUrl: './formulario-proveedor.component.html',
  styleUrls: ['./formulario-proveedor.component.css']
})
export class FormularioProveedorComponent implements OnInit {

  public proveedor: Proveedor;
  private proveedorId: number;
  private proveedorDto: any;
  public tiposProveedores: string[];
  private respuestaGetProveedor: AccionRespuesta;
  public erroresFormulario: Map<string, object>;
  public titulo: string;
  public botonTitulo: string;

  constructor(private proveedorService: ProveedorService, private router: Router, private activateRouter: ActivatedRoute) {

    this.proveedor = new Proveedor();
    this.tiposProveedores = ['PRODUCTOS', 'SERVICIOS', 'RECURSOS', 'OTROS'];
    this.erroresFormulario = new Map<string, object>();
    this.titulo = 'Nuevo proveedor';
    this.botonTitulo = 'Crear proveedor';
    this.activateRouter.params.subscribe( params => {
      console.log('Entro al constructor' + params);
      // tslint:disable-next-line: no-string-literal
      this.proveedorId = params['id'];
      if (this.proveedorId != null){
        this.getEditarProveedor();
      }
    });

   }

  ngOnInit(): void {
  }

  // Metodos del formulario
  public crearProveedorFormulario(): void {

    console.log('Estamos dentro del metodo crear formulario');

    this.proveedorService.crearProveedor(this.proveedor).subscribe( accionRespuesta => {
      // Si el resultado es true, navegamos hasta la vista
      if (accionRespuesta.resultado && accionRespuesta.id !== null ) {
        this.router.navigate(['proveedores', 'proveedor', accionRespuesta.id]);
      }
    });

    console.log('Estamos dentro del metodo crearClienteFormulario()');

    // Si tiene id, llamamos a crear, sino a editar
    if (this.proveedor != null && this.proveedor.id != null && this.proveedor.id !== 0) {

      console.log('Vamos a editar el cliente con ID: ' + this.proveedor.id);

      this.proveedorService.actualizarProveedor(this.proveedor).subscribe( accionRespuesta => {

        this.respuestaCrearEditarProveedor(accionRespuesta, true);

      }, (error => {

        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');

      }));

    } else {

      this.proveedorService.crearProveedor(this.proveedor).subscribe( accionRespuesta => {

        console.log('Vamos a crear el cliente con codigo: ' + this.proveedor.codigo);

        this.respuestaCrearEditarProveedor(accionRespuesta, false);

      }, (error => {

        swal('Servidor', 'Error, el servidor no esta disponible en este momento, intentalo mas tarde', 'error');

      }));
    }
  }

  getEditarProveedor() {

    this.proveedorService.getProveedor(this.proveedorId).toPromise().then( (accionRespuesta) => {
        try
        {
          this.respuestaGetProveedor = accionRespuesta;

          if ( this.respuestaGetProveedor.resultado )
          {
            // tslint:disable-next-line: no-string-literal
            this.proveedorDto = this.respuestaGetProveedor.data['proveedorDto'];
            this.obtenerProveedorDesdeProveedorDto(this.proveedorDto);
            this.titulo = 'Editar proveedor';
            this.botonTitulo = 'Editar proveedor';
          }

        }catch (errores){

          console.log('Se ha producido un error al transformar el cliente' + errores);
        }
      }, (error) => {
        console.log('Error, no se ha podido recuperar el cliente' + error);
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

  respuestaCrearEditarProveedor(accionRespuesta: AccionRespuesta, esEditarProveedor: boolean): void {

    console.log('Esta registrado' + accionRespuesta.resultado);
    console.log('Datos que nos devuelve spring: ' + JSON.stringify(accionRespuesta));
    // Si el resultado es true, navegamos hasta la vista
    if (accionRespuesta.resultado && accionRespuesta.id !== null ) {

      this.router.navigate(['proveedores', 'proveedor', accionRespuesta.id]);

      if (esEditarProveedor != null && esEditarProveedor ){

        swal('Proveedor editado', 'Se ha editado el proveedor correctamente', 'success');

       }else{

        swal('Nuevo proveedor', 'Se ha creado el proveedor correctamente', 'success');

       }

    }else{

      if ( accionRespuesta != null && accionRespuesta.data != null && accionRespuesta.data !=  null )
      {
        this.erroresFormulario = accionRespuesta.data;
      }else
      {
        swal('Error', 'Se ha producido un error al guardar los datos del proveedor', 'error');
      }
    }

  }



}
