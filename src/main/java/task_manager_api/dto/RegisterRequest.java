package task_manager_api.dto;

import lombok.Data;
import task_manager_api.enums.Priority;
import task_manager_api.enums.Role;
import task_manager_api.enums.Status;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private Role role;
}
