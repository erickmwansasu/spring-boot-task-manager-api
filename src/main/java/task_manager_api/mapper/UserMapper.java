package task_manager_api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import task_manager_api.dto.Enable2FaRequest;
import task_manager_api.dto.UpdatePasswordRequest;
import task_manager_api.dto.UpdateProfileRequest;
import task_manager_api.entity.User;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)

public interface UserMapper {
    void updatePassword(UpdatePasswordRequest request, @MappingTarget User entity);

    void enableTwoFactorAuth(Enable2FaRequest request, @MappingTarget User entity);

    void updateProfileRequest(UpdateProfileRequest request, @MappingTarget User entity);
}
