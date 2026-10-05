package task_manager_api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    //Whether a user updates their bio, updates their password, or toggles 2FA, API should typically return
    // the updated state of the user. This single DTO can handle all of them:
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isTwoFactorEnabled;
}
