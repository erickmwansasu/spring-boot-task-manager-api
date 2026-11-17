package task_manager_api.dto;

import lombok.Data;
import task_manager_api.enums.Priority;
import task_manager_api.enums.Status;

import java.util.Date;

@Data
public class UpdateTaskRequest {
    private String taskName;
    private String description;
    private Date dueDate;
    private Priority priority;
    private Status status;
}
