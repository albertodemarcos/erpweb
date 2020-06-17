import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BotonListadoProveedorComponent } from './boton-listado-proveedor.component';

describe('BotonListadoProveedorComponent', () => {
  let component: BotonListadoProveedorComponent;
  let fixture: ComponentFixture<BotonListadoProveedorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BotonListadoProveedorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BotonListadoProveedorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
