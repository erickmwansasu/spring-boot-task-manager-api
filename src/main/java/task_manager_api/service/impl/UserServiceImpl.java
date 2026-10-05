package task_manager_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import task_manager_api.dto.Enable2FaRequest;
import task_manager_api.dto.UpdatePasswordRequest;
import task_manager_api.dto.UpdateProfileRequest;
import task_manager_api.dto.UserResponse;
import task_manager_api.entity.User;
import task_manager_api.exception.ResourceNotFoundException;
import task_manager_api.mapper.UserMapper;
import task_manager_api.repository.UserRepository;
import task_manager_api.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserResponse updatePassword(Long id, UpdatePasswordRequest request) {
        //Fetching an existing user with the given id
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        if(!passwordEncoder.matches(request.getCurrentPassword(), existingUser.getPassword()))
            throw new IllegalArgumentException("Current password does not match!");

        String encryptedPassword = passwordEncoder.encode(request.getNewPassword());
        existingUser.setPassword(encryptedPassword);

        userRepository.save(existingUser);

        return new UserResponse();
    }

    @Override
    public UserResponse enableTwoFactorAuth(Long id, Enable2FaRequest request) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        existingUser.setTwoFactorAuth(request.isEnable2FaAuth());
        userRepository.save(existingUser);

        return new UserResponse();
    }

    @Override
    public UserResponse updateProfileRequest(Long id, UpdateProfileRequest request) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        userMapper.updateProfileRequest(request, existingUser);
        userRepository.save(existingUser);

        return new UserResponse();
    }
}
