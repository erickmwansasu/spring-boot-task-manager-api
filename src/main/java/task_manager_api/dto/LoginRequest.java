package task_manager_api.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
