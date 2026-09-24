package task_manager_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import task_manager_api.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
