package com.springcourse.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.springcourse.domain.Request;
import com.springcourse.domain.enums.RequestState;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long>{	
	//@Query("select r from request r where r.owner_id = ?1")
	public List<Request> findAllByOwnerId(Long id);
	
	@Query("update request r set r.state = ?2 where r.id = ?1")
	public Optional<Request> updateStatus(Long id, RequestState state);
}
