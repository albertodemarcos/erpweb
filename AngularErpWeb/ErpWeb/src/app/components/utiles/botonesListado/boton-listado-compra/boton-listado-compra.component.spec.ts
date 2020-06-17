import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BotonListadoCompraComponent } from './boton-listado-compra.component';

describe('BotonListadoCompraComponent', () => {
  let component: BotonListadoCompraComponent;
  let fixture: ComponentFixture<BotonListadoCompraComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BotonListadoCompraComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BotonListadoCompraComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
