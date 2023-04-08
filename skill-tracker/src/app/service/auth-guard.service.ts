import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate{

  constructor(private authService: AuthService, private router: Router) { }

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot) : boolean {
    console.log('CanActivate called');
    let isLoggedIn = this.authService.isAuthenticated();
    if (isLoggedIn){
      return true
    } else {
      this.router.navigate(['/login']);
    }
    return false;
  }

}
