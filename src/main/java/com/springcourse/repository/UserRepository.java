package com.springcourse.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springcourse.domain.User;
import com.springcourse.domain.enums.Role;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	@Query("select u from user u where u.email = ?1 and u.password = ?2")
	public Optional<User> login(String email, String password);

	@Modifying
	@Transactional(readOnly = false)
	@Query("update user u set u.role = ?2 where u.id = ?1")
	public int updateRole(Long id, Role role);
}
