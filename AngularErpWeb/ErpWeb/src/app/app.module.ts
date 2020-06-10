/* -------------------------- MODULOS PRINCIPALES -------------------------- */
import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

/* ------------------------------------------------------------------------- */

/* ------------------------ COMPONENTES PRINCIPALES ------------------------ */
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { HomeComponent } from './components/home/home.component';
import { FooterComponent } from './components/footer/footer.component';

/* ------------------------------------------------------------------------- */

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

/* ------------------------------------------------------------------------- */

/* ------------------------------- SERVICIOS ------------------------------- */

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

/* ------------------------------------------------------------------------- */

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
    FormularioVehiculoComponent
  ], // Modulos
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
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
