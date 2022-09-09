import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CURDproductComponent } from './curdproduct.component';

describe('CURDproductComponent', () => {
  let component: CURDproductComponent;
  let fixture: ComponentFixture<CURDproductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CURDproductComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CURDproductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
