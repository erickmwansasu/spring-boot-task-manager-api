package task_manager_api.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import task_manager_api.dto.Enable2FaRequest;
import task_manager_api.dto.UpdatePasswordRequest;
import task_manager_api.dto.UpdateProfileRequest;
import task_manager_api.dto.UserResponse;
import task_manager_api.entity.User;
import task_manager_api.service.impl.UserServiceImpl;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;

    @PostMapping("/update-password")
    public ResponseEntity<UserResponse> updatePassword(@AuthenticationPrincipal User user, @RequestBody UpdatePasswordRequest request) {
        Long userId = user.getId();

        return ResponseEntity.ok(userService.updatePassword(userId, request));
    }

    @PostMapping("/enable-2FA")
    public ResponseEntity<UserResponse> enableTwoFactorAuth(@AuthenticationPrincipal User user, @RequestBody Enable2FaRequest request) {
        Long userId = user.getId();

        return ResponseEntity.ok(userService.enableTwoFactorAuth(userId, request));
    }

    @PostMapping("/update-profile")
    public ResponseEntity<UserResponse> updateProfileRequest(@AuthenticationPrincipal User user, @RequestBody UpdateProfileRequest request) {
        Long userId = user.getId();

        return ResponseEntity.ok(userService.updateProfileRequest(userId, request));
    }
}
