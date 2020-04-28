import { TestBed, async, inject } from '@angular/core/testing';

import { LoginGuard } from './login.guard';

describe('StudentLoginGuard', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [LoginGuard]
    });
  });

  it('should ...', inject([LoginGuard], (guard: LoginGuard) => {
    expect(guard).toBeTruthy();
  }));
});
