package task_manager_api.dto;

import lombok.Data;

@Data
public class Enable2FaRequest {
    private boolean enable2FaAuth;
}
