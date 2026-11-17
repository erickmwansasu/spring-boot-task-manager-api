package task_manager_api.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import task_manager_api.dto.Enable2FaRequest;
import task_manager_api.dto.UpdatePasswordRequest;
import task_manager_api.dto.UpdateProfileRequest;
import task_manager_api.dto.UserResponse;
import task_manager_api.entity.User;
import task_manager_api.exception.UserNotFoundException;
import task_manager_api.mapper.UserMapper;
import task_manager_api.repository.UserRepository;
import task_manager_api.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserResponse updatePassword(@RequestBody Long id, @RequestBody UpdatePasswordRequest request) {
        //Fetching an existing user with the given id
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));
        //Some code is missing
        userRepository.save(existingUser);

        return new UserResponse();
    }

    @Override
    public UserResponse enableTwoFactorAuth(@RequestBody Long id, @RequestBody Enable2FaRequest request) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        //Some code is missing
        userRepository.save(existingUser);

        return new UserResponse();
    }

    @Override
    public UserResponse updateProfileRequest(Long id, UpdateProfileRequest request) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found!"));

        userMapper.updateProfileRequest(request, existingUser);
        userRepository.save(existingUser);

        return new UserResponse();
    }
}
