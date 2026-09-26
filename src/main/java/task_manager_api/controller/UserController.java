package task_manager_api.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import task_manager_api.dto.Enable2FaRequest;
import task_manager_api.dto.UpdatePasswordRequest;
import task_manager_api.dto.UpdateProfileRequest;
import task_manager_api.dto.UserResponse;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class UserController {
    public ResponseEntity<UserResponse> updatePassword(@RequestBody UpdatePasswordRequest request) {
        return ResponseEntity.ok(userService.updatePassword(request));
    }

    public ResponseEntity<UserResponse> enableTwoFactorAuth(@RequestBody Enable2FaRequest request) {
        return ResponseEntity.ok(userService.enable2FaAuth(request));
    }

    public ResponseEntity<UserResponse> updateProfileRequest(@RequestBody UpdateProfileRequest request) {
        return ResponseEntity.ok(userService.updateProfileRequest(request));
    }
}
