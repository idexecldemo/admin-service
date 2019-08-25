package com.idexcel.adminservice.controller;

import java.util.List;
import java.lang.reflect.Type;

import javax.servlet.http.HttpServletRequest;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.reflect.TypeToken;
import com.idexcel.adminservice.dto.LenderDTO;
import com.idexcel.adminservice.dto.LenderListDTO;
import com.idexcel.adminservice.entity.Lender;
import com.idexcel.adminservice.service.LenderService;

@RestController
@RequestMapping("idexceldemo/lenders")
public class LenderController {
	
	
	private ModelMapper modelMapper;
	
	@Autowired
	private LenderService lenderService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	PropertyMap<LenderDTO, Lender> propertiesToSkipMap = new PropertyMap<LenderDTO, Lender>() {
		@Override
		protected void configure() {
			skip().setCreatedDate(null);
			skip().setUpdatedDate(null);
			skip().setCreatedBy(null);
			skip().setUpdatedBy(null);
		}
	};
	
	@Autowired
	public LenderController(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;
		this.modelMapper.addMappings(propertiesToSkipMap);
	}
	
	/**
	 * Create new Lender
	 * @param lenderDTO
	 * @param request
	 * @return
	 */
	@PostMapping()
	public ResponseEntity<Object> createLender(@RequestBody LenderDTO lenderDTO, HttpServletRequest request) {

		Lender lender = modelMapper.map(lenderDTO, Lender.class);
		logger.info("LenderDTO=" + lenderDTO + " Lender=" + lender);
		
		String lenderId = lenderService.createLender(lender);

		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.add("Location", request.getRequestURL().toString() + "/" + lenderId);		
		return new ResponseEntity<>(responseHeaders, HttpStatus.CREATED);
	}
	
	/**
	 * Get Lender By LenderId
	 * @param lenderId
	 * @return
	 */
	@GetMapping("/{lenderId}")
	public LenderDTO getLender(@PathVariable(value= "lenderId") String lenderId) {
		final Lender lender = lenderService.getLenderById(lenderId);
		final LenderDTO lenderDTO =  modelMapper.map(lender, LenderDTO.class);
		logger.info("LenderDTO=" + lenderDTO + " Lender=" + lender);
		return lenderDTO;
		
	}
	
	/**
	 * Get All Lenders
	 * @return
	 */
	@GetMapping()
	public List<LenderListDTO> getAllLenders() {
		final List<Lender> lenders = lenderService.getAllLenders();
	    Type targetListType = new TypeToken<List<LenderListDTO>>() {
			private static final long serialVersionUID = 1L;}.getType();
	    return modelMapper.map(lenders, targetListType);

	}
	
	/**
	 * Update Lender
	 * @param lenderId
	 * @param lenderDTO
	 * @return
	 */
	@PutMapping("/{lenderId}")
	public ResponseEntity<Object> updateLender(@PathVariable(value= "lenderId") String lenderId, @RequestBody LenderDTO lenderDTO) {
		Lender lender = lenderService.getLenderById(lenderId);
		modelMapper.map(lenderDTO, lender);
		logger.info("Lender=" + lender);
		lenderService.updateLender(lender);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	@RequestMapping(path="/{lenderId}", method = RequestMethod.PATCH)
	public ResponseEntity<Object> updateLenderStatus(@PathVariable(value= "lenderId") String lenderId, @RequestBody LenderDTO lenderDTO) {
		logger.info("LenderDTO=" + lenderDTO);
		lenderService.updateLenderStatus(lenderId, lenderDTO.getStatus());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(path="/{lenderId}", method = RequestMethod.HEAD)
	public ResponseEntity<Object> getLenderInfo(@PathVariable(value= "lenderId") String lenderId) {
		lenderService.getLenderById(lenderId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping("/{lenderId}")
	public ResponseEntity<Object> deleteLender(@PathVariable(value= "lenderId") String lenderId) {
		lenderService.deleteLenderById(lenderId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
