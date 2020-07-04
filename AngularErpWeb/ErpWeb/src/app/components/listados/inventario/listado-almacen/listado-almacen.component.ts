import { Component, OnInit , AfterViewInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AlmacenService } from 'src/app/services/inventario/almacen.service';
import { Almacen } from 'src/app/model/entitys/almacen.model';

declare var jQuery: any;

@Component({
  selector: 'app-listado-almacen',
  templateUrl: './listado-almacen.component.html',
  styleUrls: ['./listado-almacen.component.css']
})
export class ListadoAlmacenComponent implements OnInit, AfterViewInit {

  public tituloListado: string;
  private jqGridId: string;
  private jqGridPagerId: string;
  private jqGridColNames: string[];
  private jqGridColModel: {};
  private jqGridData: Almacen[];

  constructor(private almacenService: AlmacenService, private router: Router, private activatedRoute: ActivatedRoute) {
    this.tituloListado = 'Listado de almcenes';
    this.jqGridId = 'almcenes-grid';
    this.jqGridPagerId = 'almcenes-pager';
    this.jqGridColNames = ['ID', 'Ver', 'Código', 'Nombre', 'Dirección', 'Población', 'Región' ];
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
      { name: 'direccion', index: '', width: '', search: true, sortable: true },
      { name: 'poblacion', index: '', width: '', search: true, sortable: true },
      { name: 'region', index: '', width: '', search: true, sortable: true }
    ];
    this.jqGridData = new Array<Almacen>();
  }

  getListadoAlmacens(): void{

    console.log('Entramos en el metodo getListadoAlmacens()');

    this.almacenService.getAlmacenes().then( (almacenes) => {
        try {
          // Introducimos los datos
          almacenes.forEach(almacen => this.jqGridData.push(almacen));
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
        console.log('Se ha pulsado sobre el boton ver para ir al almacen con id: ' + iCol);
        if (iCol === 1 )
        {
          // Obtenemos el valor de la columna oculta
          const idCelValue = jQuery( '#' + this.jqGridId ).jqGrid ('getCell', rowid, 'id');
          console.log('Se ha pulsado sobre el boton ver para ir al almacen con id: ' + idCelValue);
          this.router.navigate(['almacenes', 'almacen', idCelValue]);
        }
      }
    });

    // Filtros
    jQuery('#' + this.jqGridId).jqGrid('filterToolbar', {searchOperators : true});

    jQuery('#exportar').on('click', () => {

      console.log('Se inicia la exportacion a excel del listado de almacenes');

      jQuery('#' + this.jqGridId).tableExport({ type: 'excel', fileName: 'listado-almacenes' , escape: 'false'} );

    });

  }

  ngOnInit(): void {
    this.getListadoAlmacens();
  }

 


}
