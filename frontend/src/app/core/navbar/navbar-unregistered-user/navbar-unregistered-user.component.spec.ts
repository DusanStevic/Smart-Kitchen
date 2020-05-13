import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NavbarUnregisteredUserComponent } from './navbar-unregistered-user.component';

describe('NavbarUnregisteredUserComponent', () => {
  let component: NavbarUnregisteredUserComponent;
  let fixture: ComponentFixture<NavbarUnregisteredUserComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NavbarUnregisteredUserComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NavbarUnregisteredUserComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
