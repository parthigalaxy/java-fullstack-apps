import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { SearchProfileComponent } from './search-profile/search-profile.component';
import { AuthGuardService } from './service/auth-guard.service';
import { SkillLoginComponent } from './skill-login/skill-login.component';

const routes: Routes = [
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'search', component: SearchProfileComponent, canActivate:[AuthGuardService] },
  { path: 'login', component: SkillLoginComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
