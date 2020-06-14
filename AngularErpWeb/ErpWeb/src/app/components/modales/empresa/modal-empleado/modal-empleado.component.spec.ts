import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalEmpleadoComponent } from './modal-empleado.component';

describe('ModalEmpleadoComponent', () => {
  let component: ModalEmpleadoComponent;
  let fixture: ComponentFixture<ModalEmpleadoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModalEmpleadoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalEmpleadoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
