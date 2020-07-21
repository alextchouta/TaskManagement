package org.sid.taskmanagement.Controller;

import org.sid.taskmanagement.Entities.Person;
import org.sid.taskmanagement.Repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping("/persons/{id}")
    public Person findPersonById(@PathVariable(name = "id") Long id){

        return personRepository.findById(id).get();
    }


    @GetMapping("/persons")
    public List<Person> findAllPersons(){

        return personRepository.findAll();
    }

    @PostMapping("/persons")
    public Person CreateAndSavePerson(@RequestBody Person person){
        return this.personRepository.save(person);
    }

    @PutMapping("/persons/{id}")
    public Person updatePerson(@PathVariable(name = "id") Long id, @RequestBody Person person){
        person.setId(id);
        return personRepository.save(person);

    }

    @DeleteMapping("/persons/{id}")
    public void deleteTaskById(@PathVariable(name = "id") Long id){
        this.personRepository.deleteById(id);
    }

    @DeleteMapping("persons")
    public void deleteAllTasks(){
        this.personRepository.deleteAll();
    }
}
