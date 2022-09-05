package net.corejava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.corejava.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	public abstract User findByEmail(String email);
}
