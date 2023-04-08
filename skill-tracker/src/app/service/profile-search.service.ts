import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProfileSearchService {

  baseUrl:string = 'http://localhost:8000/';

  constructor(private http:HttpClient) { }


  userLogin(userName:string, password:string){
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin':'*',
      'Accept': '*/*' });
    let options = { headers: headers };
    return this.http.post(this.baseUrl+'skill-tracker-server/api/v1/user/login',{'userName': userName,'password':password}, options);
  }

  searchSkills(selectedSearchType:string, searchValue:string, page:number){
    if(selectedSearchType === undefined || selectedSearchType === ''){
      selectedSearchType = 'ALL';
      searchValue = 'ALL';
    }
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin':'*',
      'Accept': '*/*',
      'Authorization': 'Bearer '+localStorage.getItem('user-token')});
      let options = { headers: headers };
    return this.http.get(this.baseUrl+'skill-tracker-processor/skill/'+selectedSearchType+'/'+searchValue+'/'+page, options);
  }

}
