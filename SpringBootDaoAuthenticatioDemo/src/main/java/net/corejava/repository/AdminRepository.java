package net.corejava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.corejava.entity.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{
	public abstract Admin findByEmail(String email);
}
