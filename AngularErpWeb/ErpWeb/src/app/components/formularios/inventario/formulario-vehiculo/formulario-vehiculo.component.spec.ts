import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormularioVehiculoComponent } from './formulario-vehiculo.component';

describe('FormularioVehiculoComponent', () => {
  let component: FormularioVehiculoComponent;
  let fixture: ComponentFixture<FormularioVehiculoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormularioVehiculoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormularioVehiculoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
