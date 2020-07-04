import { Component, OnInit , AfterViewInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { EmpleadoService } from 'src/app/services/empresa/empleado.service';
import { Empleado } from 'src/app/model/entitys/empleado.model';

declare var jQuery: any;

@Component({
  selector: 'app-listado-empleados',
  templateUrl: './listado-empleados.component.html',
  styleUrls: ['./listado-empleados.component.css']
})
export class ListadoEmpleadosComponent implements OnInit, AfterViewInit {

  public tituloListado: string;
  private jqGridId: string;
  private jqGridPagerId: string;
  private jqGridColNames: string[];
  private jqGridColModel: {};
  private jqGridData: Empleado[];

  constructor(private empleadoService: EmpleadoService, private router: Router, private activatedRoute: ActivatedRoute) {
    this.tituloListado = 'Listado de empleados';
    this.jqGridId = 'empleados-grid';
    this.jqGridPagerId = 'empleados-pager';
    this.jqGridColNames = ['ID', 'Ver', 'Código', 'Nombre', 'Apellido', 'CIF/NIF', 'Teléfono', 'Tipo Empleado'];
    this.jqGridColModel = [
      { name: 'id', index: '', hidden: true},
      { name: '', index: '', width: '60', height: '50', align: 'center', search: false, sortable: false, formatter:
        () => {
          return '<button class="btn btn-primary btn-xs" style="margin: 0%; width: 15 px; height: 30px">' +
          '<i class="fa fa-search-minus" aria-hidden="true"></i></button>';
        }
      },
      { name: 'codigo', index: '', width: '', search: true, sortable: true },
      { name: 'nombre', index: '', width: '', search: true, sortable: true },
      { name: 'apellido', index: '', width: '', search: true, sortable: true },
      { name: 'nif', index: '', width: '', search: true, sortable: true },
      { name: 'telefono', index: '', width: '', search: true, sortable: true },
      { name: 'tipoEmpleado', index: '', width: '', search: true, sortable: true}
    ];
    this.jqGridData = new Array<Empleado>();
  }

  getListadoEmpleados(): void{

    console.log('Entramos en el metodo getListadoEmpleados()');

    this.empleadoService.getEmpleados().then( (empleados) => {
        try {
          // Introducimos los datos
          empleados.forEach(empleado => this.jqGridData.push(empleado));
          // Reload JqGrid
          jQuery('#' + this.jqGridId).jqGrid('setGridParam', {data: this.jqGridData}).trigger('reloadGrid');
        } catch (errores){
          console.error('Se ha producido un error al convertir la infomracion del servidor' + errores);
        }
      }, (error) => {
        console.log('Error, no se ha obtenido la informacion');
      }
    );
  }

  ngAfterViewInit(): void {

    // JqGrid
    ( jQuery ('#' + this.jqGridId ) ).jqGrid({
      colNames: this.jqGridColNames,
      colModel: this.jqGridColModel,
      pager: this.jqGridPagerId,
      caption: '',
      rowNum: 10,
      rowList: [10, 20],
      viewrecords: true,
      gridview: true,
      autowidth: false,
      onCellSelect: (rowid: any, iCol: any, cellcontent: any, e: any) => {
        // Si se pulsa sobre la columna 1, pulsan sobre el boton
        console.log('Se ha pulsado sobre el boton ver para ir al empleado con id: ' + iCol);
        if (iCol === 1 )
        {
          // Obtenemos el valor de la columna oculta
          const idCelValue = jQuery( '#' + this.jqGridId ).jqGrid ('getCell', rowid, 'id');
          console.log('Se ha pulsado sobre el boton ver para ir al empleado con id: ' + idCelValue);
          this.router.navigate(['rrhh', 'empleado', idCelValue]);
        }
      }
    });

    // Filtros
    jQuery('#' + this.jqGridId).jqGrid('filterToolbar', {searchOperators : true});

    jQuery('#exportar').on('click', () => {

      console.log('Se inicia la exportacion a excel del listado de empleados');

      jQuery('#' + this.jqGridId).tableExport({ type: 'excel', fileName: 'listado-empleados' , escape: 'false'} );

    });

  }

  ngOnInit(): void {
    this.getListadoEmpleados();
  }



}
