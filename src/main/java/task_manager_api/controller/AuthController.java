package task_manager_api.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import task_manager_api.dto.AuthResponse;
import task_manager_api.dto.LoginRequest;
import task_manager_api.dto.RefreshTokenRequest;
import task_manager_api.dto.RegisterRequest;
import task_manager_api.service.impl.AuthService;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) { return authService.register(request); }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest request) { return authService.login(request); }

    @PostMapping("/refresh")
    public AuthResponse refresh(@RequestBody RefreshTokenRequest request) { return authService.refreshAccessToken(request.getRefreshToken()); }
}
