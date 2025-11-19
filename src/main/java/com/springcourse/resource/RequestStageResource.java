package com.springcourse.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springcourse.DTO.RequestStageSaveDTO;
import com.springcourse.domain.RequestStage;
import com.springcourse.service.RequestStageService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value="request-stages")
public class RequestStageResource {
	@Autowired RequestStageService requestStageService;
	
	@PostMapping public ResponseEntity<RequestStage> save(@RequestBody @Valid RequestStageSaveDTO requestStage)
	{
		RequestStage saved = requestStageService.save(requestStage.transformToRequestStage());
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<RequestStage> getById(@PathVariable Long id)
	{
		RequestStage found = requestStageService.getById(id);
		return ResponseEntity.ok(found);
	}
}