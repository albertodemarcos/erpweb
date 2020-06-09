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

/* ------------------------------------------------------------------------- */

@NgModule({
  declarations: [
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
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})

export class AppModule { }
