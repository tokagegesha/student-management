import { TestBed, inject } from '@angular/core/testing';

import { ExchangeProgrammeService } from './exchange-programme.service';

describe('ExchangeProgrammeService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [ExchangeProgrammeService]
    });
  });

  it('should be created', inject([ExchangeProgrammeService], (service: ExchangeProgrammeService) => {
    expect(service).toBeTruthy();
  }));
});
