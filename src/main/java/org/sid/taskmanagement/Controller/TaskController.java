package org.sid.taskmanagement.Controller;

import org.sid.taskmanagement.Entities.Task;
import org.sid.taskmanagement.Repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/tasks/{id}")
    public Task findTaskById(@PathVariable(name = "id") Long id){
        return taskRepository.findById(id).get();
    }

    @GetMapping("/tasks")
    public List<Task> findAllTasks(){

        return taskRepository.findAll();
    }

    @PostMapping("/tasks")
    public Task  CreateAndSaveTask(@RequestBody Task task){
        return this.taskRepository.save(task);
    }

    @PutMapping("/tasks/{id}")
    public Task updateTask(@PathVariable(name = "id") Long id, @RequestBody Task task){
        task.setId(id);
        return taskRepository.save(task);

    }

    @DeleteMapping("/tasks/{id}")
    public void deleteTaskById(@PathVariable(name = "id") Long id){
        this.taskRepository.deleteById(id);
    }

    @DeleteMapping("tasks")
    public void deleteAllTasks(){
        this.taskRepository.deleteAll();
    }
}
