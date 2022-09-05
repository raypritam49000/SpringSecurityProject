package com.employee.management.system.api.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.employee.management.system.api.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	public abstract Optional<User> findByUsername(String username);
	public abstract Boolean existsByUsername(String username);
	public abstract Boolean existsByEmail(String email);
}
