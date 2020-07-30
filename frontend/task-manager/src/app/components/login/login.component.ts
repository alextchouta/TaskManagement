import { Component, OnInit } from '@angular/core';
import {AuthentificationService} from "../../services/authentification.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  mode: number = 0;

  constructor(private authService:AuthentificationService,
              private router:Router) { }

  ngOnInit() {
    let token = this.authService.loadToken();
    if(token){
      this.router.navigateByUrl("/tasks")
    }
  }

  onRegister() {
    this.router.navigateByUrl("/register");
  }

  onLogin(formData) {
    console.log("Data" + " " + formData);
    this.authService.login(formData).subscribe(resp =>{
   let token = resp.headers.get('Authorization');
   this.authService.saveToken(token);
   this.router.navigateByUrl("/tasks");
    }, error => {
      this.mode=1;
    });
  }
}
