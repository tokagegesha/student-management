import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ExchangeProgrammesComponent } from './exchange-programmes.component';

describe('ExchangeProgrammesComponent', () => {
  let component: ExchangeProgrammesComponent;
  let fixture: ComponentFixture<ExchangeProgrammesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ExchangeProgrammesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ExchangeProgrammesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
