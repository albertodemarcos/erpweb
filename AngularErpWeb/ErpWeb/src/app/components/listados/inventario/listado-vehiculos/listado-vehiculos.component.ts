import { Component, OnInit , AfterViewInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { VehiculoService } from 'src/app/services/inventario/vehiculo.service';
import { Vehiculo } from 'src/app/model/entitys/vehiculo.model';

declare var jQuery: any;
declare var TableExport: any;

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
  private tableExport: any;

  constructor(private vhiculoService: VehiculoService, private router: Router, private activatedRoute: ActivatedRoute) {
    this.tituloListado = 'Listado de vehículos';
    this.jqGridId = 'vehiculos-grid';
    this.jqGridPagerId = 'vehiculos-pager';
    this.jqGridColNames = ['ID', 'Ver', 'Código', 'Marca', 'Modelo', 'Matricula', 'Tipo', 'Tipo Combustible', 'F. matriculación'];
    this.jqGridColModel = [
      { name: 'id', index: '', hidden: true},
      { name: '', index: '', width: '60', height: '50', align: 'center', search: false, sortable: false, formatter:
        () => {
          return '<button class="btn btn-primary btn-xs" style="margin: 0%; width: 15 px; height: 30px">' +
          '<i class="fa fa-search-minus" aria-hidden="true"></i></button>';
        }
      },
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
      autowidth: true,
      shrinkToFit: true,
      guiStyle: 'bootstrap4',
      iconSet: 'fontAwesome',
      loadonce: false,
        searching: {
        multipleSearch: true,
        loadDefaults: false,
        sopt: ['eq', 'ne', 'cn', 'bw', 'bn', 'ge', 'le', 'lt', 'gt'],
        showQuery: false
      },
      navOptions: { add: false, edit: false, search: false, del: false, refresh: true, refreshstate: 'current' },
      onCellSelect: (rowid: any, iCol: any, cellcontent: any, e: any) => {
        // Si se pulsa sobre la columna 1, pulsan sobre el boton
        console.log('Se ha pulsado sobre el boton ver para ir al vehiculo con id: ' + iCol);
        if (iCol === 1 )
        {
          // Obtenemos el valor de la columna oculta
          const idCelValue = jQuery( '#' + this.jqGridId ).jqGrid ('getCell', rowid, 'id');
          console.log('Se ha pulsado sobre el boton ver para ir al vehiculo con id: ' + idCelValue);
          this.router.navigate(['vehiculos', 'vehiculo', idCelValue]);
        }
      }
    }).jqGrid('navGrid');

    // Filtros
    jQuery('#' + this.jqGridId).jqGrid('filterToolbar', {searchOperators : true});

    // Refrescar
    jQuery('#refresh_' + this.jqGridId).on('click', () => {
      // Limpiamos previamente
      this.jqGridData = new Array<Vehiculo>();
      // Pedimos los datos
      this.getListadoVehiculos();
    });

    // Exportar a Excel
    jQuery('#exportar').on('click', () => {

      console.log('Se inicia la exportacion a excel del listado de vehiculos');

      // Inicializamos la varaible
      this.tableExport = new TableExport(jQuery('#' + this.jqGridId), {
        header: true,
        footers: true,
        formats: ['xlsx', 'csv', 'txt'],
        filename : 'listado_vehiculos',
        bootstrap: true,
        exportButtons: false,
        position: 'bottom',
        ignoreRows: null,
        ignoreCols: [0, 2],
        trimWhitespace: true,
        RTL: false,
        sheetname: 'id'
      });

      // Obtenemos los datos
      const exportData = this.tableExport.getExportData()['vehiculos-grid'].xlsx;

      // Exportamos los datos
      this.tableExport.export2file(
        exportData.data,
        exportData.mimeType,
        exportData.filename,
        exportData.fileExtension
      );
    });

  }

  ngOnInit(): void {
    this.getListadoVehiculos();
  }


}
