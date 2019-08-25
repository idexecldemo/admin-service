package com.idexcel.adminservice.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.idexcel.adminservice.dto.LenderDTO;
import com.idexcel.adminservice.entity.Lender;
import com.idexcel.adminservice.enums.LenderStatus;
import com.idexcel.adminservice.service.LenderService;

@RestController
@RequestMapping("idexceldemo/lenders")
public class LenderController {
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private LenderService lenderService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PostMapping()
	public ResponseEntity<Object> createLender(@RequestBody LenderDTO lenderDTO, HttpServletRequest request) {

		Lender lender = modelMapper.map(lenderDTO, Lender.class);
		lender.setStatus(LenderStatus.PENDING);
		logger.info("LenderDto=" + lenderDTO + " Lender=" + lender);
		
		String lenderId = lenderService.createLender(lender);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Location", request.getRequestURL().toString() + lenderId);		
		return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
	}
	
	@GetMapping("/{lenderId}")
	public LenderDTO getLender(@PathVariable(value= "lenderId") String lenderId) {
		Lender lender = lenderService.getLenderById(lenderId);
		logger.info("Lender=" + lender);
		LenderDTO lenderDTO =  modelMapper.map(lender, LenderDTO.class);
		logger.info("LenderDTO=" + lenderDTO);
		
		return lenderDTO;
		
	}

}
