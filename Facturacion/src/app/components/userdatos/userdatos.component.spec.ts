import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserdatosComponent } from './userdatos.component';

describe('UserdatosComponent', () => {
  let component: UserdatosComponent;
  let fixture: ComponentFixture<UserdatosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UserdatosComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UserdatosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
