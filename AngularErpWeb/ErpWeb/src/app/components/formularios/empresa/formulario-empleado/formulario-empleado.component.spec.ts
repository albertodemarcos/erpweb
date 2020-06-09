import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormularioEmpleadoComponent } from './formulario-empleado.component';

describe('FormularioEmpleadoComponent', () => {
  let component: FormularioEmpleadoComponent;
  let fixture: ComponentFixture<FormularioEmpleadoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormularioEmpleadoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormularioEmpleadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
