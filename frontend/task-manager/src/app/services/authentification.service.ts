import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {JwtHelperService} from "@auth0/angular-jwt";
import {User} from "../components/model/User";
import {UserTask} from "../components/model/Task";
import {Observable} from "rxjs";


@Injectable({
  providedIn: 'root'
})
export class AuthentificationService {
  private host:string="http://localhost:8080";
  private jwToken:string;
  public roles:Array<any>[];
  admin:number = 0;
  constructor(private http:HttpClient) { }

 public login(user:User){

    if(user.username === "admin"){
     this.admin= 1;
   }
    console.log("request : " + " " + this.host+"/login");
    return this.http.post(this.host+"/login", user,{observe: 'response'});
  }

 public  register(user){
    return this.http.post(this.host+"/users",user);
  }

 public  saveToken(jwToken){
    this.jwToken = jwToken;
    localStorage.setItem("token", jwToken);
    let jwHelper = new JwtHelperService();
    this.roles = jwHelper.decodeToken(this.jwToken).roles;
    console.log("roles" + " " + this.roles);
  }

  public getTasks(){
    if(this.jwToken==null) this.loadToken();
    return this.http.get(this.host+"/tasks",{headers: new HttpHeaders({'Authorization':this.jwToken})})
  }

  public saveTask(task){
    let headers = new HttpHeaders();
    headers.append('Authorization', this.jwToken);
    return this.http.post(this.host+"/tasks",task,{headers: new HttpHeaders({'Authorization':this.jwToken})});
  }

  public loadToken() {
  this.jwToken= localStorage.getItem('token');
  return this.jwToken;
  }

  public logout(){
    localStorage.removeItem('token');
    this.admin = 0;
  }

  updateTask(id:number, task){
    let headers = new HttpHeaders();
    headers.append('Authorization', this.jwToken);
    return this.http.put("http://localhost:8080/tasks/"+id, task,{headers: new HttpHeaders({'Authorization':this.jwToken})});
  }

  getTaskById(id:number):Observable<UserTask>{
    return this.http.get<UserTask>("http://localhost:8080/tasks/"+id,{headers: new HttpHeaders({'Authorization':this.jwToken})});
  }

  deleteById(id:number){
    return this.http.delete("http://localhost:8080/tasks/"+id,{headers: new HttpHeaders({'Authorization':this.jwToken})});
  }
}
