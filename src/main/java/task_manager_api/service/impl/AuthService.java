package task_manager_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import task_manager_api.dto.AuthResponse;
import task_manager_api.dto.LoginRequest;
import task_manager_api.dto.RegisterRequest;
import task_manager_api.entity.RefreshToken;
import task_manager_api.entity.User;
import task_manager_api.repository.RefreshTokenRepository;
import task_manager_api.repository.UserRepository;
import task_manager_api.security.JwtService;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(@RequestBody RegisterRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(request.getRole())
                .build();

        userRepository.save(user);
        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = refreshTokenService.createRefreshToken(user).getToken();

        return new AuthResponse(accessToken, refreshToken);
    }

    public AuthResponse login(@RequestBody LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow();

        String accessToken = jwtService.generateAccessToken(user);
        String refreshToken = refreshTokenService.createRefreshToken(user).getToken();

        return new AuthResponse(accessToken, refreshToken);
    }

    public AuthResponse refreshAccessToken(String refreshTokenValue) {
        RefreshToken refreshToken = refreshTokenService.verifyExpiry(refreshTokenValue);
        User user = refreshToken.getUser();

        String newAccessToken = jwtService.generateAccessToken(user);
        return new AuthResponse(newAccessToken, refreshTokenValue);
    }
}
