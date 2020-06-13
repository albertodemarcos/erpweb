import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListadoIngresosComponent } from './listado-ingresos.component';

describe('ListadoIngresosComponent', () => {
  let component: ListadoIngresosComponent;
  let fixture: ComponentFixture<ListadoIngresosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListadoIngresosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListadoIngresosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
