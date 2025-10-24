package com.springcourse.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springcourse.domain.Request;
import com.springcourse.domain.enums.RequestState;
import com.springcourse.repository.RequestRepository;

@Service
public class RequestService {
	@Autowired RequestRepository requestRepository;
	
	public Request save(Request request)
	{
		request.setState(RequestState.OPEN);
		request.setCreationDate(new Date());
		Request response = requestRepository.save(request);
		return response;
	}
	
	public Request update(Request request)
	{
		Request response = requestRepository.save(request);
		return response;
	}
	
	public Request getById(Long id)
	{
		Optional<Request> request = requestRepository.findById(id);
		return request.get();
	}
	
	public List<Request> getList()
	{
		List<Request> requests = requestRepository.findAll();
		return requests;
	}
	
	public List<Request> listAll()
	{
		List<Request> requests = requestRepository.findAll();
		return requests;
	}
	
	public List<Request> listByAllByOwnerId(Long ownerId)
	{
		List<Request> requests = requestRepository.findAllByOwnerId(ownerId);
		return requests;
	}
}
