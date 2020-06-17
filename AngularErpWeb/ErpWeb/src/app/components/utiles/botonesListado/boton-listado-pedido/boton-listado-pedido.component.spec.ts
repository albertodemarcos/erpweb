import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BotonListadoPedidoComponent } from './boton-listado-pedido.component';

describe('BotonListadoPedidoComponent', () => {
  let component: BotonListadoPedidoComponent;
  let fixture: ComponentFixture<BotonListadoPedidoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BotonListadoPedidoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BotonListadoPedidoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
