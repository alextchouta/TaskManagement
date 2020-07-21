package org.sid.taskmanagement.Repositories;

import org.sid.taskmanagement.Entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task,Long> {
}
