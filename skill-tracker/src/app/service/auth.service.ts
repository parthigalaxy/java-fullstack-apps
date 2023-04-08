import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { User } from './skill-tracker';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private activeUser = new BehaviorSubject<User>({name:'',role:'',status:false});
  isActiveUser = this.activeUser.asObservable();

  constructor() { }

  isAuthenticated():boolean{
    const userToekn = localStorage.getItem('user-token');
    console.log('userToekn > ', userToekn);
    console.log('userToekn Validation > ', userToekn != null && userToekn.length > 0);
    if(userToekn != null && userToekn.length > 0){
      let userDetails = userToekn?.split('.')[1];
      console.log('userDetails > ', atob(''+userDetails));
      let user = {name:JSON.parse(atob(''+userDetails))?.sub,role:JSON.parse(atob(''+userDetails))?.role,status:userToekn != null && userToekn.length > 0 }
      this.activeUser.next(user);
    }else{
      this.activeUser.next({name:'',role:'',status:false});
    }

    return userToekn != null && userToekn.length > 0 ;
  }

}
