/* -------------------------- MODULOS PRINCIPALES -------------------------- */
import { BrowserModule } from '@angular/platform-browser';
import { LOCALE_ID, NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { FullCalendarModule } from '@fullcalendar/angular';

// FullCalendar
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import listPlugin from '@fullcalendar/list';
import interactionPlugin from '@fullcalendar/interaction';
import { registerLocaleData } from '@angular/common';
import localEs from '@angular/common/locales/es'; // localeEs

// CKEDITOR
import { CKEditorModule } from '@ckeditor/ckeditor5-angular';

// Autocompletar
import { AutocompleteLibModule } from 'angular-ng-autocomplete';

/* --------------------------------------------------- COMPONENTES ------------------------------------------------------- */

/* --------------------------------------------------- PRINCIPALES ------------------------------------------------------ */
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomeComponent } from './components/home/home.component';
import { PlanificadorComponent } from './components/calendario/planificador/planificador.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';

/* ------------------------------------------------- VISUALIZACION ENTITYS --------------------------------------------- */
import { ClienteComponent } from './components/cards/crm/cliente/cliente.component';
import { ContratoComponent } from './components/cards/ventas/contrato/contrato.component';
import { VentaComponent } from './components/cards/ventas/venta/venta.component';
import { FacturaComponent } from './components/cards/ventas/factura/factura.component';
import { CompraComponent } from './components/cards/compras/compra/compra.component';
import { PedidoComponent } from './components/cards/compras/pedido/pedido.component';
import { ProveedorComponent } from './components/cards/compras/proveedor/proveedor.component';
import { AlmacenComponent } from './components/cards/inventario/almacen/almacen.component';
import { ArticuloComponent } from './components/cards/inventario/articulo/articulo.component';
import { VehiculoComponent } from './components/cards/inventario/vehiculo/vehiculo.component';
import { EmpleadoComponent } from './components/cards/empresas/empleado/empleado.component';
import { EmpresaComponent } from './components/cards/empresas/empresa/empresa.component';
import { UsuarioComponent } from './components/cards/usuarios/usuario/usuario.component';
import { StockComponent } from './components/cards/inventario/stock/stock.component';

/* ------------------------------------------------------ LISTADOS ------------------------------------------------------ */
import { ListadoClientesComponent } from './components/listados/crm/listado-clientes/listado-clientes.component';
import { ListadoContratosComponent } from './components/listados/ventas/listado-contratos/listado-contratos.component';
import { ListadoVentasComponent } from './components/listados/ventas/listado-ventas/listado-ventas.component';
import { ListadoFacturasComponent } from './components/listados/ventas/listado-facturas/listado-facturas.component';
import { ListadoComprasComponent } from './components/listados/compras/listado-compras/listado-compras.component';
import { ListadoPedidosComponent } from './components/listados/compras/listado-pedidos/listado-pedidos.component';
import { ListadoProveedoresComponent } from './components/listados/compras/listado-proveedores/listado-proveedores.component';
import { ListadoArticulosComponent } from './components/listados/inventario/listado-articulos/listado-articulos.component';
import { ListadoAlmacenComponent } from './components/listados/inventario/listado-almacen/listado-almacen.component';
import { ListadoStockComponent } from './components/listados/inventario/listado-stock/listado-stock.component';
import { ListadoVehiculosComponent } from './components/listados/inventario/listado-vehiculos/listado-vehiculos.component';
import { ListadoEmpleadosComponent } from './components/listados/empresa/listado-empleados/listado-empleados.component';
import { ListadoUsuariosComponent } from './components/listados/usuarios/listado-usuarios/listado-usuarios.component';

/* ----------------------------------------------------------------------------------------------------------------------------------- */

/* ---------------------------------------------------- FORMULARIOS ----------------------------------------------------------- */
import { FormularioClienteComponent } from './components/formularios/cllientes/formulario-cliente/formulario-cliente.component';
import { FormularioEmpleadoComponent } from './components/formularios/empresa/formulario-empleado/formulario-empleado.component';
import { FormularioEmpresaComponent } from './components/formularios/empresa/formulario-empresa/formulario-empresa.component';
import { FormularioVentaComponent } from './components/formularios/ventas/formulario-venta/formulario-venta.component';
import { FormularioContratoComponent } from './components/formularios/ventas/formulario-contrato/formulario-contrato.component';
import { FormularioFacturaComponent } from './components/formularios/ventas/formulario-factura/formulario-factura.component';
import { FormularioCompraComponent } from './components/formularios/compras/formulario-compra/formulario-compra.component';
import { FormularioPedidoComponent } from './components/formularios/compras/formulario-pedido/formulario-pedido.component';
import { FormularioProveedorComponent } from './components/formularios/compras/formulario-proveedor/formulario-proveedor.component';
import { FormularioAlmacenComponent } from './components/formularios/inventario/formulario-almacen/formulario-almacen.component';
import { FormularioArticuloComponent } from './components/formularios/inventario/formulario-articulo/formulario-articulo.component';
import { FormularioVehiculoComponent } from './components/formularios/inventario/formulario-vehiculo/formulario-vehiculo.component';
import { FormularioUsuarioComponent } from './components/formularios/usuarios/formulario-usuario/formulario-usuario.component';
import { FormularioStockComponent } from './components/formularios/inventario/formulario-stock/formulario-stock.component';

/* ---------------------------------------------------------------------------------------------------------------------------------- */

/* -------------------------------------------------- MODALES ----------------------------------------------------------------------- */

import { ModalArticuloComponent } from './components/modales/inventario/modal-articulo/modal-articulo.component';

/* ----------------------------------------------------------------------------------------------------------------------------------- */

/* --------------------------------------------------- SERVICIOS --------------------------------------------------------------------- */

import { CompraService } from './services/compras/compra.service';
import { PedidoService } from './services/compras/pedido.service';
import { ProveedorService } from './services/compras/proveedor.service';
import { ClienteService } from './services/crm/cliente.service';
import { EmpleadoService } from './services/empresa/empleado.service';
import { EmpresaService } from './services/empresa/empresa.service';
import { AlmacenService } from './services/inventario/almacen.service';
import { ArticuloService } from './services/inventario/articulo.service';
import { VehiculoService } from './services/inventario/vehiculo.service';
import { UsuarioService } from './services/usuarios/usuario.service';
import { ContratoService } from './services/ventas/contrato.service';
import { FacturaService } from './services/ventas/factura.service';
import { VentaService } from './services/ventas/venta.service';
/* --------------------------------------------------------------------------------------------------------------------------- */

/* --------------------------------------------------- PIPES ----------------------------------------------------------------- */

import { CapitalizarPipe } from './Pipes/capitalizar.pipe';

/* ------------------------------------------------------------------------------------------------------------------------- */

registerLocaleData(localEs);

// Pluggin FullCalendar
FullCalendarModule.registerPlugins([
  dayGridPlugin,
  timeGridPlugin,
  listPlugin,
  interactionPlugin
]);

@NgModule({
  declarations: [ // Componentes
    AppComponent,
    NavbarComponent,
    HomeComponent,
    FormularioClienteComponent,
    FormularioEmpleadoComponent,
    FormularioEmpresaComponent,
    FormularioVentaComponent,
    FormularioContratoComponent,
    FormularioFacturaComponent,
    FormularioCompraComponent,
    FormularioPedidoComponent,
    FormularioProveedorComponent,
    FormularioAlmacenComponent,
    FormularioArticuloComponent,
    FormularioVehiculoComponent,
    ListadoClientesComponent,
    ListadoContratosComponent,
    ListadoVentasComponent,
    ListadoFacturasComponent,
    ListadoComprasComponent,
    ListadoPedidosComponent,
    ListadoProveedoresComponent,
    ListadoArticulosComponent,
    ListadoAlmacenComponent,
    ListadoVehiculosComponent,
    ListadoEmpleadosComponent,
    ListadoStockComponent,
    ModalArticuloComponent,
    PlanificadorComponent,
    ClienteComponent,
    ContratoComponent,
    VentaComponent,
    FacturaComponent,
    CompraComponent,
    PedidoComponent,
    ProveedorComponent,
    ArticuloComponent,
    AlmacenComponent,
    VehiculoComponent,
    EmpleadoComponent,
    EmpresaComponent,
    ListadoUsuariosComponent,
    FormularioUsuarioComponent,
    UsuarioComponent,
    LoginComponent,
    LogoutComponent,
    CapitalizarPipe,
    ListadoStockComponent,
    StockComponent,
    FormularioStockComponent
  ],
  imports: [  // Modulos
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    FullCalendarModule,
    CKEditorModule,
    AutocompleteLibModule
  ],
  providers: [ // Servicios
    CompraService,
    PedidoService,
    ProveedorService,
    ClienteService,
    EmpleadoService,
    EmpresaService,
    AlmacenService,
    ArticuloService,
    VehiculoService,
    UsuarioService,
    ContratoService,
    FacturaService,
    VentaService,
    { provide: LOCALE_ID, useValue: 'es' }
  ],
  bootstrap: [AppComponent]
})

export class AppModule { }
