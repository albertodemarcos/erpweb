import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListadoGastosComponent } from './listado-gastos.component';

describe('ListadoGastosComponent', () => {
  let component: ListadoGastosComponent;
  let fixture: ComponentFixture<ListadoGastosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListadoGastosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListadoGastosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
