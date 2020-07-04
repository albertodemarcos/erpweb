import { Component, OnInit, AfterViewInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FacturaService } from 'src/app/services/ventas/factura.service';
import { Factura } from 'src/app/model/entitys/factura.model';

declare var jQuery: any;

@Component({
  selector: 'app-listado-facturas',
  templateUrl: './listado-facturas.component.html',
  styleUrls: ['./listado-facturas.component.css']
})
export class ListadoFacturasComponent implements OnInit, AfterViewInit {

  public tituloListado: string;
  private jqGridId: string;
  private jqGridPagerId: string;
  private jqGridColNames: string[];
  private jqGridColModel: {};
  private jqGridData: Factura[];

  constructor(private facturaService: FacturaService, private router: Router, private activatedRoute: ActivatedRoute) {
    this.tituloListado = 'Listado de Facturas';
    this.jqGridId = 'facturas-grid';
    this.jqGridPagerId = 'facturas-pager';
    this.jqGridColNames = ['ID', 'Ver', 'Código', 'F. Inicio', 'F. Fín', 'Base Impl.', 'Importe Total' ];
    this.jqGridColModel = [
      { name: 'id', index: '', hidden: true},
      { name: '', index: '', width: '60', height: '50', align: 'center', search: false, sortable: false, formatter:
        () => {
          return '<button class="btn btn-primary btn-xs" style="margin: 0%; width: 15 px; height: 30px">' +
          '<i class="fa fa-search-minus" aria-hidden="true"></i></button>';
        }
      },
      { name: 'codigo', index: '', width: '', search: true, sortable: true },
      { name: 'fechaInicio', index: '', width: '', search: true, sortable: true },
      { name: 'fechaFin', index: '', width: '', search: true, sortable: true },
      { name: 'baseImponibleTotal', index: '', width: '', search: true, sortable: true },
      { name: 'importeTotal', index: '', width: '', search: true, sortable: true }
    ];
    this.jqGridData = new Array<Factura>();
  }

  getListadoFacturas(): void{

    console.log('Entramos en el metodo getListadoFacturas()');

    this.facturaService.getFacturas().then( (facturas) => {
        try {
          // Introducimos los datos
          facturas.forEach(factura => this.jqGridData.push(factura));
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
        console.log('Se ha pulsado sobre el boton ver para ir a la factura con id: ' + iCol);
        if (iCol === 1 )
        {
          // Obtenemos el valor de la columna oculta
          const idCelValue = jQuery( '#' + this.jqGridId ).jqGrid ('getCell', rowid, 'id');
          console.log('Se ha pulsado sobre el boton ver para ir a la factura con id: ' + idCelValue);
          this.router.navigate(['facturas', 'factura', idCelValue]);
        }
      }
    });

    // Filtros
    jQuery('#' + this.jqGridId).jqGrid('filterToolbar', {searchOperators : true});

    jQuery('#exportar').on('click', () => {

      console.log('Se inicia la exportacion a excel del listado de facturas');

      jQuery('#' + this.jqGridId).tableExport({ type: 'excel', fileName: 'listado-facturas' , escape: 'false'} );

    });

  }

  ngOnInit(): void {
    this.getListadoFacturas();
  }


}
