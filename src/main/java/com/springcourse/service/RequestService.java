package com.springcourse.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springcourse.domain.Request;
import com.springcourse.domain.enums.RequestState;
import com.springcourse.exception.NotFoundException;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
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
		return request.orElseThrow(() -> new NotFoundException("There are not requests with id "+ id));
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
	
	public PageModel<Request> listAllOnLazyMode(PageRequestModel pr)
	{
		Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
		Page<Request> page = requestRepository.findAll(pageable); 
		return new PageModel<>((int)page.getTotalElements(),page.getSize(),page.getTotalPages(), page.getContent());
	}
	
	public PageModel<Request> listAllByOwnerIdOnLazyMode(Long ownerId, PageRequestModel pr)
	{
		Pageable pageable = PageRequest.of(pr.getPage(), pr.getSize());
		Page<Request> page = requestRepository.findAllByOwnerId(ownerId, pageable);
		return new PageModel<>((int)page.getTotalElements(),page.getSize(),page.getTotalPages(), page.getContent());
	}
}
