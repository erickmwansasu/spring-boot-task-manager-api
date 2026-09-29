package task_manager_api.service;

import org.springframework.web.bind.annotation.RequestBody;
import task_manager_api.dto.Enable2FaRequest;
import task_manager_api.dto.UpdatePasswordRequest;
import task_manager_api.dto.UpdateProfileRequest;
import task_manager_api.dto.UserResponse;

public interface UserService {
    public UserResponse updatePassword(Long id, UpdatePasswordRequest request);

    public UserResponse enableTwoFactorAuth(Long id, Enable2FaRequest request);

    public UserResponse updateProfileRequest(Long id, UpdateProfileRequest request);
}
