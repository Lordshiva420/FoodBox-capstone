import { TestBed } from '@angular/core/testing';

import { adminGuard } from './admin.gaurd';

describe('adminGuard', () => {
  let guard: adminGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    guard = TestBed.inject(adminGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
