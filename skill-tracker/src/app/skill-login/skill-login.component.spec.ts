import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SkillLoginComponent } from './skill-login.component';

describe('SkillLoginComponent', () => {
  let component: SkillLoginComponent;
  let fixture: ComponentFixture<SkillLoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SkillLoginComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SkillLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
