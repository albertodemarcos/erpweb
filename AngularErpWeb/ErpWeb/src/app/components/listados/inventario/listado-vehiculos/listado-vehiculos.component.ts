import { Component, OnInit , AfterViewInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { VehiculoService } from 'src/app/services/inventario/vehiculo.service';
import { Vehiculo } from 'src/app/model/entitys/vehiculo.model';

declare var jQuery: any;

@Component({
  selector: 'app-listado-vehiculos',
  templateUrl: './listado-vehiculos.component.html',
  styleUrls: ['./listado-vehiculos.component.css']
})
export class ListadoVehiculosComponent implements OnInit, AfterViewInit {

  public tituloListado: string;
  private jqGridId: string;
  private jqGridPagerId: string;
  private jqGridColNames: string[];
  private jqGridColModel: {};
  private jqGridData: Vehiculo[];

  constructor(private vhiculoService: VehiculoService, private router: Router, private activatedRoute: ActivatedRoute) {
    this.tituloListado = 'Listado de vehículos';
    this.jqGridId = 'vehiculos-grid';
    this.jqGridPagerId = 'vehiculos-pager';
    this.jqGridColNames = ['', 'Código', 'Marca', 'Modelo', 'Matricula', 'Tipo', 'Tipo Combustible', 'F. matriculación'];
    this.jqGridColModel = [
      { name: 'id', index: '', width: '40', search: false, sortable: false },
      { name: 'codigo', index: '', width: '', search: true, sortable: true },
      { name: 'marca', index: '', width: '', search: true, sortable: true },
      { name: 'modelo', index: '', width: '', search: true, sortable: true },
      { name: 'matricula', index: '', width: '', search: true, sortable: true },
      { name: 'tipoVehiculo', index: '', width: '', search: true, sortable: true},
      { name: 'tipoCombustible', index: '', width: '', search: true, sortable: true },
      { name: 'fechaMatriculacion', index: '', width: '', search: true, sortable: true }
    ];
    this.jqGridData = new Array<Vehiculo>();
  }

  getListadoVehiculos(): void{

    console.log('Entramos en el metodo getListadoVehiculos()');

    this.vhiculoService.getVehiculos().then( (vehiculos) => {
        try {
          // Introducimos los datos
          vehiculos.forEach(vehiculo => this.jqGridData.push(vehiculo));
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
      autowidth: true
    });

    // Filtros
    jQuery('#' + this.jqGridId).jqGrid('filterToolbar', {searchOperators : true});

    jQuery('#exportar').on('click', () => {

      console.log('Se inicia la exportacion a excel del listado de vehiculos');

      jQuery('#' + this.jqGridId).tableExport({ type: 'excel', fileName: 'listado-vehiculos' , escape: 'false'} );

    });

  }

  ngOnInit(): void {
    this.getListadoVehiculos();
  }


}
