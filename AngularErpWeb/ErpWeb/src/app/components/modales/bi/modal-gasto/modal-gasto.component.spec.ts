import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ModalGastoComponent } from './modal-gasto.component';

describe('ModalGastoComponent', () => {
  let component: ModalGastoComponent;
  let fixture: ComponentFixture<ModalGastoComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ModalGastoComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ModalGastoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
