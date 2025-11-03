package com.springcourse.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springcourse.DTO.RequestSaveDTO;
import com.springcourse.DTO.RequestUpdateDTO;
import com.springcourse.domain.Request;
import com.springcourse.domain.RequestStage;
import com.springcourse.model.PageModel;
import com.springcourse.model.PageRequestModel;
import com.springcourse.service.RequestService;
import com.springcourse.service.RequestStageService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "requests")
public class RequestResource {
	@Autowired RequestService requestService;
	
	@Autowired RequestStageService requestStageService;
	
	@PostMapping
	public ResponseEntity<Request> save(@RequestBody @Valid RequestSaveDTO request)
	{
		Request createdRequest = requestService.save(request.transformToRequest());
		return ResponseEntity.status(HttpStatus.CREATED).body(createdRequest);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Request> update(@PathVariable Long id, @RequestBody @Valid RequestUpdateDTO request)
	{
		Request validatedRequest = request.transformToRequest();
		validatedRequest.setId(id);
		Request updatedRequest = requestService.update(validatedRequest);
		return ResponseEntity.ok(updatedRequest);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Request> getById(@PathVariable Long id)
	{
		Request foundRequest = requestService.getById(id);
		return ResponseEntity.ok(foundRequest);
	}
	
	@GetMapping
	public ResponseEntity<PageModel<Request>> listAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size)
	{
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<Request> pm = requestService.listAllOnLazyMode(pr);
		return ResponseEntity.ok(pm);
	}
	
	@GetMapping("/{id}/request-stages")
	public ResponseEntity<PageModel<RequestStage>> listAllRequestStagesById(@PathVariable Long id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size)
	{
		PageRequestModel pr = new PageRequestModel(page, size);
		PageModel<RequestStage> pm = requestStageService.listAllByRequestIdOnLazyMode(id, pr);
		return ResponseEntity.ok(pm);
	}
}
