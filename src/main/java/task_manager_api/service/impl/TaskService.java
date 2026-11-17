package task_manager_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import task_manager_api.dto.CreateTaskRequest;
import task_manager_api.dto.DeleteTaskRequest;
import task_manager_api.dto.TaskResponse;
import task_manager_api.entity.Task;
import task_manager_api.exception.TaskNotFoundException;
import task_manager_api.repository.TaskRepository;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskResponse createTask(@RequestBody CreateTaskRequest request) {
        Task task = Task.builder()
                .taskName(request.getTaskName())
                .description(request.getDescription())
                .dueDate(request.getDueDate())
                .priority(request.getPriority())
                .status(request.getStatus())
                .build();

        taskRepository.save(task);

        return new TaskResponse();
    }

    public TaskResponse deleteTask(@RequestBody Long taskId, @RequestBody DeleteTaskRequest request) {
        Task existingTask = taskRepository.findById(taskId)
                .orElseThrow(() -> new TaskNotFoundException("Task not found!"));

        //Need to check if the task belongs to the user deleting it
        taskRepository.delete(existingTask);

        return new TaskResponse();
    }
}
