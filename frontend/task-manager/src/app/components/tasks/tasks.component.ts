import { Component, OnInit } from '@angular/core';
import {AuthentificationService} from "../../services/authentification.service";
import {Router} from "@angular/router";


@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css']
})
export class TasksComponent implements OnInit {
public tasks:any;
  constructor(public authService:AuthentificationService, private router:Router
              ) { }

  ngOnInit() {
    console.log("Value admin:" + " " + this.authService.admin);
    this.authService.getTasks().subscribe(data =>{
      this.tasks = data;
      console.log(data);
    }, err =>{
      this.authService.logout();
      this.router.navigateByUrl("/login");
    })
  }

  onNewTask() {
  this.router.navigateByUrl('/new-task')
  }

  onEditTask(id: number) {
    this.router.navigate(["editTask", id]);
  }
}
