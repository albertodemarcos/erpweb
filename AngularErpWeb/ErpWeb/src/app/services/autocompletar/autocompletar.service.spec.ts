import { TestBed } from '@angular/core/testing';

import { AutocompletarService } from './autocompletar.service';

describe('AutocompletarService', () => {
  let service: AutocompletarService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AutocompletarService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
