package org.sid.taskmanagement.Repositories;

import org.sid.taskmanagement.Entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {
}
