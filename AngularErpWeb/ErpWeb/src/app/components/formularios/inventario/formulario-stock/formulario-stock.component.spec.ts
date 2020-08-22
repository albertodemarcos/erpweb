import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { FormularioStockComponent } from './formulario-stock.component';

describe('FormularioStockComponent', () => {
  let component: FormularioStockComponent;
  let fixture: ComponentFixture<FormularioStockComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ FormularioStockComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(FormularioStockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
