import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {EditorItsealfComponent} from './editor-itsealf.component';

describe('EditorItsealfComponent', () => {
  let component: EditorItsealfComponent;
  let fixture: ComponentFixture<EditorItsealfComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EditorItsealfComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EditorItsealfComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
