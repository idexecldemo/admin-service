package com.idexcel.adminservice.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idexcel.adminservice.dao.LenderServiceRepository;
import com.idexcel.adminservice.entity.Lender;
import com.idexcel.adminservice.exceptions.LenderAlreadyExistsException;
import com.idexcel.adminservice.service.LenderService;

@Service
public class LenderServiceImpl implements LenderService {

	@Autowired
	LenderServiceRepository lenderServiceRepository;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public String createLender(Lender lender) {
		logger.info("Creating Lender:" + lender);
		
		List<Lender> existingLender = lenderServiceRepository.findByName(lender.getName());
		if (existingLender != null && existingLender.size() > 0) {
			throw new LenderAlreadyExistsException("Lender with name '" + lender.getName() + "' already exists");
		}
		
		Lender lenderCreated = lenderServiceRepository.save(lender);
		return lenderCreated.getId();
	}

	@Override
	public Lender getLenderById(String lenderId) {
		return lenderServiceRepository.findByLenderId(lenderId);
	}

}
