import { Component, OnInit } from '@angular/core';
import {TasksComponent} from "../tasks/tasks.component";
import {UserTask} from "../model/Task";
import {AuthentificationService} from "../../services/authentification.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-edit-task',
  templateUrl: './edit-task.component.html',
  styleUrls: ['./edit-task.component.css']
})
export class EditTaskComponent implements OnInit {
  mode: number =1;
  task: UserTask
  idTask:number;

  constructor(private authService:AuthentificationService,
              private router:Router,
              private activatedRoute:ActivatedRoute) {
    this.idTask = activatedRoute.snapshot.params['id'];
  }

  ngOnInit() {
    this,this.getTaskById();
  }

  updateTask() {

  this.authService.updateTask(this.task.id, this.task).subscribe(data =>{
    alert('UPDATE OK');
    this.mode = 2;
  });
  }

  getTaskById(){
    this.authService.getTaskById(this.idTask).subscribe(data =>{
      this.task = data;
    })
  }
}
