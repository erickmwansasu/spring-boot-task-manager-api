package task_manager_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task_manager_api.entity.Task;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {
    Optional<Task> findByTaskName(String taskName);
}
