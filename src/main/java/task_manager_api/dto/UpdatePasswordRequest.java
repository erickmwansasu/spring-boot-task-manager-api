package task_manager_api.dto;

import lombok.Data;

@Data
public class UpdatePasswordRequest {
    private Long id;
    private String currentPassword;
    private String newPassword;
}
