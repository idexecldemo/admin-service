package com.idexcel.adminservice.controller;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idexcel.adminservice.dto.LenderDto;
import com.idexcel.adminservice.entity.Lender;
import com.idexcel.adminservice.service.LenderService;

@RestController
@RequestMapping("/lenders")
public class LenderController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private LenderService lenderService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PostMapping()
	public ResponseEntity createLender(@RequestBody LenderDto lenderDto, HttpServletRequest request) {

		Lender lender = modelMapper.map(lenderDto, Lender.class);
		logger.info("LenderDto=" + lenderDto + " Lender=" + lender);
		
		String lenderId = lenderService.createLender(lender);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Location", request.getRequestURL().toString() + lenderId);		
		return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
	}
	
	@GetMapping("/test")
	public String test() {
		return "hello";
	}

}
