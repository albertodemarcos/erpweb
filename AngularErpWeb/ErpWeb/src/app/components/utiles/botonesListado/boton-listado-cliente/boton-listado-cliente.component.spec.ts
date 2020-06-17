import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { BotonListadoClienteComponent } from './boton-listado-cliente.component';

describe('BotonListadoClienteComponent', () => {
  let component: BotonListadoClienteComponent;
  let fixture: ComponentFixture<BotonListadoClienteComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ BotonListadoClienteComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BotonListadoClienteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
