package com.androjavatech4u.repository;

import java.util.List;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.androjavatech4u.entities.Book;
import com.androjavatech4u.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	User findByEmail(@Param("email") String email);

	@Query("SELECT u FROM User u WHERE u.email = :e AND u.pass = :p")
	User findByEmailPass(@Param("e") String email, @Param("p") String pass);

	public List<User> findTop10ByOrderByIdDesc();

	public List<User> findByimageNamesContaining(String imageName);

	public User findByMobileOrEmail(String mobile, String email);

}
