import {inject, TestBed} from '@angular/core/testing';

import {SignInService} from './sign-in.service';

describe('SignInService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [SignInService]
    });
  });

  it('should be created', inject([SignInService], (service: SignInService) => {
    expect(service).toBeTruthy();
  }));
});
