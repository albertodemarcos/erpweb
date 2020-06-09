import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormularioIngresoComponent } from './formulario-ingreso.component';

describe('FormularioIngresoComponent', () => {
  let component: FormularioIngresoComponent;
  let fixture: ComponentFixture<FormularioIngresoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormularioIngresoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormularioIngresoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
