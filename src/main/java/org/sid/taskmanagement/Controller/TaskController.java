package org.sid.taskmanagement.Controller;

import org.sid.taskmanagement.Entities.Task;
import org.sid.taskmanagement.Repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class TaskController {

    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> findTaskById(@PathVariable(name = "id") Long id) {
        Optional<Task> taskData = taskRepository.findById(id);

        if (taskData.isPresent()) {
            return new ResponseEntity<>(taskData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

/*    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> findAllTasks() {
        List<Task> tasks = new ArrayList<>();
        try {
            taskRepository.findAll().forEach(task -> tasks.add(task));

            if (tasks.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(tasks, HttpStatus.NO_CONTENT);
        } catch (Exception e) {

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }*/

@GetMapping("/tasks")
public List<Task> listTasks(){
    return taskRepository.findAll();
}

    @PostMapping("/tasks")
    public ResponseEntity<Task> CreateAndSaveTask(@RequestBody Task task) {
        try {
            return new ResponseEntity<>(this.taskRepository.save(task), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }

    }

/*    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable(name = "id") Long id, @RequestBody Task task) {
        Optional<Task> taskData = taskRepository.findById(id);

        if (taskData.isPresent()) {
            Task _task = taskData.get();
            _task.setPerson(task.getPerson());
            _task.setState(task.getState());
            _task.setName(task.getName());
            _task.setDescription(task.getDescription());

            return new ResponseEntity<>(taskRepository.save(_task), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }*/


    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.PUT)
    public Task update(@PathVariable  Long id, @RequestBody Task task){
        task.setId(id);
        return taskRepository.save(task);
    }

    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<HttpStatus> deleteTaskById(@PathVariable(name = "id") Long id) {
        try {
            this.taskRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }

    }

    @DeleteMapping("tasks")
    public ResponseEntity<HttpStatus> deleteAllTasks() {
        try {
            this.taskRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
