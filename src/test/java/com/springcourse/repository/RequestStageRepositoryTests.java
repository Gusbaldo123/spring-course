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
import com.springcourse.domain.RequestStage;
import com.springcourse.domain.User;
import com.springcourse.domain.enums.RequestState;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class RequestStageRepositoryTests {
	@Autowired RequestStageRepository requestStageRepository;
	
	@Test
	@Order(1)
	public void saveTest()
	{
		Request request = new Request();
		request.setId(1L);
		User owner = new User();
		owner.setId(1L);
		
		RequestStage requestStage = new RequestStage(null, "Comprado um notebook", new Date(), RequestState.CLOSED, request, owner);
		RequestStage response =requestStageRepository.save(requestStage);
		assertThat(response.getId()).isEqualTo(1);
	}
	
	@Test
	@Order(2)
	public void getByIdTest()
	{
		Optional<RequestStage> foundTest =requestStageRepository.findById(1L);
		assertThat(foundTest.map(RequestStage::getDescription).orElse(null)).isEqualTo("Comprado um notebook");
	}
	
	@Test
	@Order(3)
	public void listByRequestIdTest()
	{
		List<RequestStage> stages = requestStageRepository.findAllByRequestId(1L);
		assertThat(stages.size()).isGreaterThan(0);
	}
}
