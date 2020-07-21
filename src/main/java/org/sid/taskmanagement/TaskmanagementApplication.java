package org.sid.taskmanagement;

import org.sid.taskmanagement.Entities.Person;
import org.sid.taskmanagement.Entities.Task;
import org.sid.taskmanagement.Enumeration.TaskState;
import org.sid.taskmanagement.Repositories.PersonRepository;
import org.sid.taskmanagement.Repositories.TaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TaskmanagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskmanagementApplication.class, args);
    }

    @Bean
    CommandLineRunner start (TaskRepository taskRepository, PersonRepository personRepository){
        return args -> {

            // une Personne peut faire plusieurs taches differentes
            Person p1 = new Person(1L, "Alex", null);
            personRepository.save(p1);
            Person p2 = new Person(2L, "Ted", null);
            personRepository.save(p2);
            Person p3 = new Person(3L, "JAVA", null);
            personRepository.save(p3);

            // une tache ne peut etre attribuee qu a une seule personne
            Task task1 = new Task(1L, "Task-01", "backend", TaskState.COMMITED, p1);
            taskRepository.save(task1);
            Task task2 = new Task(2L, "Task-02", "frontend", TaskState.COMMITED, p2);
            taskRepository.save(task2);
        }

        ;
    }

}
