package task_manager_api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import task_manager_api.dto.UpdateTaskRequest;
import task_manager_api.entity.Task;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)

public interface TaskMapper {
    void updateTaskRequest(UpdateTaskRequest request, @MappingTarget Task entity);
}
