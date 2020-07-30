import { Component, OnInit } from '@angular/core';
import {AuthentificationService} from "../../services/authentification.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  private user: any;
  mode:number=0;
  private errorMessage: string;
  constructor(private authService:AuthentificationService) { }

  ngOnInit() {
  }

  onRegister(user) {
    this.authService.register(user).subscribe(data=>{
      console.log(" new Task" + " " + data);
      this.user=data;
      this.mode=1;
    },error => {
      this.errorMessage = error.message;
      this.mode = 0;
    })
  }

}
