package org.sid.taskmanagement;

import org.sid.taskmanagement.Entities.Person;
import org.sid.taskmanagement.Entities.Task;
import org.sid.taskmanagement.Enumeration.TaskState;
import org.sid.taskmanagement.Repositories.PersonRepository;
import org.sid.taskmanagement.Repositories.TaskRepository;
import org.sid.taskmanagement.security.entities.AppRole;
import org.sid.taskmanagement.security.repositories.AppUserRepository;
import org.sid.taskmanagement.security.services.AccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.stream.Stream;

@SpringBootApplication
public class TaskmanagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskmanagementApplication.class, args);
    }

    @Bean
    CommandLineRunner start (TaskRepository taskRepository, PersonRepository personRepository, AccountService accountService, AppUserRepository appUserRepository){
        return args -> {

            // une Personne peut faire plusieurs taches differentes
            Person p1 = new Person(1L, "Alex", null);
            personRepository.save(p1);
            Person p2 = new Person(2L, "Ted", null);
            personRepository.save(p2);
            Person p3 = new Person(3L, "JAVA", null);
            personRepository.save(p3);

            // une tache ne peut etre attribuee qu a une seule personne
            Task task1 = new Task(1L, "Task-01", "backend", "COMMITED", p1);
            taskRepository.save(task1);
            Task task2 = new Task(2L, "Task-02", "frontend", "COMMITED", p2);
            taskRepository.save(task2);

            // Save les roles
            accountService.saveAppRole(new AppRole(null, "USER"));
            accountService.saveAppRole(new AppRole(null, "ADMIN"));

            Stream.of("user1", "user2", "user3", "admin").forEach(u ->{
                accountService.saveUser(u, "1234", "1234");
            });
            accountService.addRoleToUser("admin", "ADMIN");
            appUserRepository.findAll().forEach(u->{
                System.out.println("Name:" + " " + u.getUsername() + " " + "Roles" + " " + u.getRoles());
            });
        };


    }

    @Bean
    BCryptPasswordEncoder getBPCE(){
        return new BCryptPasswordEncoder();
    }

}
