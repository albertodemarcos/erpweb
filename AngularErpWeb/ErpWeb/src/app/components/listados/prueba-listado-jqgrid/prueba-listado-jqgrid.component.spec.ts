import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PruebaListadoJqgridComponent } from './prueba-listado-jqgrid.component';

describe('PruebaListadoJqgridComponent', () => {
  let component: PruebaListadoJqgridComponent;
  let fixture: ComponentFixture<PruebaListadoJqgridComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PruebaListadoJqgridComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PruebaListadoJqgridComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
