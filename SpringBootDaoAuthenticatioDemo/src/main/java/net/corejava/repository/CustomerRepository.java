package net.corejava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.corejava.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	public abstract Customer findByEmail(String email);
}
