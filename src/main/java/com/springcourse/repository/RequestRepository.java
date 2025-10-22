package com.springcourse.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springcourse.domain.Request;
import com.springcourse.domain.enums.RequestState;

@Repository
public interface RequestRepository extends JpaRepository<Request, Long>{	
	//@Query("select r from request r where r.owner_id = ?1")
	public List<Request> findAllByOwnerId(Long id);
	
	@Transactional(readOnly=false)
	@Modifying
	@Query("update request r set r.state = ?2 where r.id = ?1")
	public int updateStatus(Long id, RequestState state);
}
