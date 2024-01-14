import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListeOfusersComponent } from './liste-ofusers.component';

describe('ListeOfusersComponent', () => {
  let component: ListeOfusersComponent;
  let fixture: ComponentFixture<ListeOfusersComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListeOfusersComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListeOfusersComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
