import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListadoContratosComponent } from './listado-contratos.component';

describe('ListadoContratosComponent', () => {
  let component: ListadoContratosComponent;
  let fixture: ComponentFixture<ListadoContratosComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListadoContratosComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListadoContratosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
