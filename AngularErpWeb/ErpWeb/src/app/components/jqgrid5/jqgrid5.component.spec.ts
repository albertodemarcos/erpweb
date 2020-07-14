import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Jqgrid5Component } from './jqgrid5.component';

describe('Jqgrid5Component', () => {
  let component: Jqgrid5Component;
  let fixture: ComponentFixture<Jqgrid5Component>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Jqgrid5Component ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Jqgrid5Component);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
