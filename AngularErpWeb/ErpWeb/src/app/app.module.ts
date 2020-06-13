/* -------------------------- MODULOS PRINCIPALES -------------------------- */
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AgGridModule } from 'ag-grid-angular';

/* ------------------------------------------------- COMPONENTES ------------------------------------------------------- */

/* ------------------------ COMPONENTES PRINCIPALES ------------------------ */
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomeComponent } from './components/home/home.component';
import { FooterComponent } from './components/footer/footer.component';

/* ------------------------------ LISTADOS ------------------------------ */
import { ListadoClientesComponent } from './components/listados/crm/listado-clientes/listado-clientes.component';
import { ListadoIngresosComponent } from './components/listados/bi/listado-ingresos/listado-ingresos.component';
import { ListadoGastosComponent } from './components/listados/bi/listado-gastos/listado-gastos.component';
import { ListadoResultadosComponent } from './components/listados/bi/listado-resultados/listado-resultados.component';
import { ListadoContratosComponent } from './components/listados/ventas/listado-contratos/listado-contratos.component';
import { ListadoVentasComponent } from './components/listados/ventas/listado-ventas/listado-ventas.component';
import { ListadoFacturasComponent } from './components/listados/ventas/listado-facturas/listado-facturas.component';
import { ListadoComprasComponent } from './components/listados/compras/listado-compras/listado-compras.component';
import { ListadoPedidosComponent } from './components/listados/compras/listado-pedidos/listado-pedidos.component';
import { ListadoProveedoresComponent } from './components/listados/compras/listado-proveedores/listado-proveedores.component';
import { ListadoArticulosComponent } from './components/listados/inventario/listado-articulos/listado-articulos.component';
import { ListadoAlmacenComponent } from './components/listados/inventario/listado-almacen/listado-almacen.component';
import { ListadoVehiculosComponent } from './components/listados/inventario/listado-vehiculos/listado-vehiculos.component';
import { ListadoEmpleadosComponent } from './components/listados/empresa/listado-empleados/listado-empleados.component';

/* ------------------------------ FORMULARIOS ------------------------------ */
import { FormularioClienteComponent } from './components/formularios/cllientes/formulario-cliente/formulario-cliente.component';
import { FormularioEmpleadoComponent } from './components/formularios/empresa/formulario-empleado/formulario-empleado.component';
import { FormularioEmpresaComponent } from './components/formularios/empresa/formulario-empresa/formulario-empresa.component';
import { FormularioVentaComponent } from './components/formularios/ventas/formulario-venta/formulario-venta.component';
import { FormularioContratoComponent } from './components/formularios/ventas/formulario-contrato/formulario-contrato.component';
import { FormularioFacturaComponent } from './components/formularios/ventas/formulario-factura/formulario-factura.component';
import { FormularioGastoComponent } from './components/formularios/bi/formulario-gasto/formulario-gasto.component';
import { FormularioIngresoComponent } from './components/formularios/bi/formulario-ingreso/formulario-ingreso.component';
import { FormularioCompraComponent } from './components/formularios/compras/formulario-compra/formulario-compra.component';
import { FormularioPedidoComponent } from './components/formularios/compras/formulario-pedido/formulario-pedido.component';
import { FormularioProveedorComponent } from './components/formularios/compras/formulario-proveedor/formulario-proveedor.component';
import { FormularioAlmacenComponent } from './components/formularios/inventario/formulario-almacen/formulario-almacen.component';
import { FormularioArticuloComponent } from './components/formularios/inventario/formulario-articulo/formulario-articulo.component';
import { FormularioVehiculoComponent } from './components/formularios/inventario/formulario-vehiculo/formulario-vehiculo.component';

/* ----------------------------------------------------------------------------------------------------------------------------------- */


/* --------------------------------------------------- SERVICIOS --------------------------------------------------------------------- */

import { GastoService } from './services/bi/gasto.service';
import { InformeService } from './services/bi/informe.service';
import { IngresoService } from './services/bi/ingreso.service';
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

/* ------------------------------------------------------------------------------------------------------------------------- */

@NgModule({
  declarations: [ // Componentes
    AppComponent,
    NavbarComponent,
    HomeComponent,
    FooterComponent,
    FormularioClienteComponent,
    FormularioEmpleadoComponent,
    FormularioEmpresaComponent,
    FormularioVentaComponent,
    FormularioContratoComponent,
    FormularioFacturaComponent,
    FormularioGastoComponent,
    FormularioIngresoComponent,
    FormularioCompraComponent,
    FormularioPedidoComponent,
    FormularioProveedorComponent,
    FormularioAlmacenComponent,
    FormularioArticuloComponent,
    FormularioVehiculoComponent,
    ListadoClientesComponent,
    ListadoIngresosComponent,
    ListadoGastosComponent,
    ListadoResultadosComponent,
    ListadoContratosComponent,
    ListadoVentasComponent,
    ListadoFacturasComponent,
    ListadoComprasComponent,
    ListadoPedidosComponent,
    ListadoProveedoresComponent,
    ListadoArticulosComponent,
    ListadoAlmacenComponent,
    ListadoVehiculosComponent,
    ListadoEmpleadosComponent
  ], // Modulos
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    AgGridModule.withComponents([])
  ],
  providers: [ // Servicios
    GastoService,
    InformeService,
    IngresoService,
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
    VentaService
  ],
  bootstrap: [AppComponent]
})

export class AppModule { }
