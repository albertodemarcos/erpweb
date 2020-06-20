import { Component, OnInit, AfterViewInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { ContratoService } from 'src/app/services/ventas/contrato.service';
import { Contrato } from 'src/app/model/entitys/contrato.model';

declare var jQuery: any;

@Component({
  selector: 'app-listado-contratos',
  templateUrl: './listado-contratos.component.html',
  styleUrls: ['./listado-contratos.component.css']
})
export class ListadoContratosComponent implements OnInit, AfterViewInit {

  public tituloListado: string;
  private jqGridId: string;
  private jqGridPagerId: string;
  private jqGridColNames: string[];
  private jqGridColModel: {};
  private jqGridData: Contrato[];

  constructor(private contratoService: ContratoService, private router: Router, private activatedRoute: ActivatedRoute) {
    this.tituloListado = 'Listado de Contratos';
    this.jqGridId = 'contratos-grid';
    this.jqGridPagerId = 'contratos-pager';
    this.jqGridColNames = ['', 'Código', 'F. Inicio', 'F. Fín', 'Base Impl.', 'Importe Total' ];
    this.jqGridColModel = [
      { name: 'id', index: '', width: '40', search: false, sortable: false },
      { name: 'codigo', index: '', width: '', search: true, sortable: true },
      { name: 'fechaInicio', index: '', width: '', search: true, sortable: true },
      { name: 'fechaFin', index: '', width: '', search: true, sortable: true },
      { name: 'baseImponibleTotal', index: '', width: '', search: true, sortable: true },
      { name: 'importeTotal', index: '', width: '', search: true, sortable: true }
    ];
    this.jqGridData = new Array<Contrato>();
  }

  getListadoContratos(): void{

    console.log('Entramos en el metodo getListadoContratos()');

    this.contratoService.getContratos().then( (contratos) => {
        try {
          // Introducimos los datos
          contratos.forEach(contrato => this.jqGridData.push(contrato));
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

      console.log('Se inicia la exportacion a excel del listado de contratos');

      jQuery('#' + this.jqGridId).tableExport({ type: 'excel', fileName: 'listado-contratos' , escape: 'false'} );

    });
  }

  ngOnInit(): void {
    this.getListadoContratos();
  }

}
