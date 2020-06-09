import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormularioContratoComponent } from './formulario-contrato.component';

describe('FormularioContratoComponent', () => {
  let component: FormularioContratoComponent;
  let fixture: ComponentFixture<FormularioContratoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormularioContratoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormularioContratoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
