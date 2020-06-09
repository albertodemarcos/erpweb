import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormularioAlmacenComponent } from './formulario-almacen.component';

describe('FormularioAlmacenComponent', () => {
  let component: FormularioAlmacenComponent;
  let fixture: ComponentFixture<FormularioAlmacenComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormularioAlmacenComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormularioAlmacenComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
