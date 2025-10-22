package com.springcourse.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springcourse.domain.Request;
import com.springcourse.domain.User;
import com.springcourse.domain.enums.RequestState;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class RequestRepositoryTests {
	@Autowired RequestRepository requestRepository;
	
	@Test
	@Order(1)
	public void saveTest()
	{
		User owner = new User();
		owner.setId(1L);
		Request request = new Request(null, "Novo Notebook HP", "Compra de notebook", new Date(), RequestState.OPEN, owner, null);
		Request response = requestRepository.save(request);
		assertThat(response.getId()).isEqualTo(1L);
	}
	
	@Test
	@Order(2)
	public void updateTest()
	{
		String desc = "Compra de notebook Atualizado";
		User owner = new User();
		owner.setId(1L);
		Request request = new Request(1L, "Novo Notebook HP Atualizado", desc, null, RequestState.OPEN, owner, null);
		Request response = requestRepository.save(request);
		assertThat(response.getDescription()).isEqualTo(desc);
	}
	
	@Test
	@Order(3)
	public void getByIdTest()
	{
		Optional<Request> foundTest = requestRepository.findById(1L);
		assertThat(foundTest.map(Request::getSubject).orElse(null)).isEqualTo("Novo Notebook HP Atualizado");
	}
	
	@Test
	@Order(4)
	public void listTest()
	{
		List<Request> list = requestRepository.findAll();
		assertThat(list.size()).isGreaterThan(0);
	}
	
	@Test
	@Order(5)
	public void listByOwnerId()
	{
		List<Request> list = requestRepository.findAllByOwnerId(1L);
		assertThat(list.size()).isGreaterThan(0);
	}
	
	@Test
	@Order(5)
	public void updateStatusTest()
	{
		int affectedRows = requestRepository.updateStatus(1L, RequestState.IN_PROGRESS);
		assertThat(affectedRows).isEqualTo(1);
	}
}
