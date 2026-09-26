package task_manager_api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import task_manager_api.dto.AuthResponse;
import task_manager_api.dto.RegisterRequest;
import task_manager_api.service.impl.AuthService;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AuthService authService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/create")
    public AuthResponse createUser(@RequestBody RegisterRequest request) { return authService.register(request); }
}
