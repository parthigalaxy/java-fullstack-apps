import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from './service/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {

  isLoggedIn:boolean = this.authService.isAuthenticated();
  userName:string = '';
  title = 'skill-tracker';
  
  constructor(private authService: AuthService, private router: Router) { 

    this.authService.isActiveUser.subscribe( activeUser => {
      if(!activeUser.status){
        localStorage.removeItem('user-token');
        this.router.navigate(['/login']);
      }
      this.userName = activeUser.name;
      this.isLoggedIn = activeUser.status;
    });

  }
  
  logout(){
    localStorage.removeItem('user-token');
    this.authService.isAuthenticated();
    this.router.navigate(['/login']);
  }

}
