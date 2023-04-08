import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../service/auth.service';
import { ProfileSearchService } from '../service/profile-search.service';

@Component({
  selector: 'app-skill-login',
  templateUrl: './skill-login.component.html',
  styleUrls: ['./skill-login.component.scss']
})
export class SkillLoginComponent {

  hide = true;
  isLoginError:boolean = false;
  errorMessage:string = '';

  constructor(private authService: AuthService,private service:ProfileSearchService, private router: Router){

    if(this.authService.isAuthenticated()){
      this.router.navigate(['/search']);
    }

  }

  skillLoginForm = new FormGroup({
    skillUserName : new FormControl('', [Validators.required, Validators.minLength(3), Validators.maxLength(30)]),
    skillUserPassword : new FormControl('', [Validators.required, Validators.minLength(5), Validators.maxLength(20)])
  });


  getUserNameErrorMessage() {
    this.isLoginError = false;

    if(this.skillUserName?.invalid){
      if (this.skillUserName.hasError('required')) {
        return 'User Name is required';
      }
      if (this.skillUserName.hasError('minlength')) {
        return 'User name should be minimum 3 characters';
      }
      if (this.skillUserName.hasError('maxlength')) {
        return 'User name should be lesthen 30 characters';
      }
    }

    return this.skillLoginForm.invalid ? 'Please Provide Valid input' : '';
  }

  getPasswordErrorMessage() {
    this.isLoginError = false;

    if(this.skillUserPassword?.invalid){
      if (this.skillUserPassword.hasError('required')) {
        return 'Password is required';
      }
      if (this.skillUserPassword.hasError('minlength')) {
        return 'Password should be minimum 5 characters';
      }
      if (this.skillUserPassword.hasError('maxlength')) {
        return 'Password should be lesthen 20 characters';
      }
    }

    return this.skillLoginForm.invalid ? 'Please Provide Valid input' : '';
  }

  onLogin(){
    this.isLoginError = false;
    if(this.skillLoginForm.valid){

      const username  = this.skillUserName?.value;
      const password = this.skillUserPassword?.value;

      this.service.userLogin(''+username, ''+password).subscribe({
          next: response => {
            console.log(JSON.parse(JSON.stringify(response)).token);
            localStorage.setItem('user-token',JSON.parse(JSON.stringify(response)).token);
            this.router.navigate(['/search']);
          },
          error: error => {
            console.log(error);
            this.isLoginError = true;
            this.errorMessage = 'Invalid Username and password';
          }
      });
      console.log("username > ", username, " password > ", password);
    }
  }

  get skillUserName() { return this.skillLoginForm.get('skillUserName'); }

  get skillUserPassword() { return this.skillLoginForm.get('skillUserPassword'); }

}
