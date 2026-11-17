package task_manager_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import task_manager_api.enums.Priority;
import task_manager_api.enums.Status;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskResponse {
    private Long id;
    private String taskName;
    private String description;
    private Date dueDate;
    private Priority priority;
    private Status status;
}
