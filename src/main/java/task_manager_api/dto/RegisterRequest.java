package task_manager_api.dto;

import lombok.Data;
import task_manager_api.enums.Role;

@Data
public class RegisterRequest {
    private String email;
    private String password;
    private Role role;
}
