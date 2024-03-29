package com.idexcel.adminservice.service.impl;

import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.idexcel.adminservice.dao.LenderServiceRepository;
import com.idexcel.adminservice.entity.Lender;
import com.idexcel.adminservice.enums.LenderStatus;
import com.idexcel.adminservice.exceptions.LenderAlreadyExistsException;
import com.idexcel.adminservice.exceptions.LenderNotFoundException;
import com.idexcel.adminservice.service.LenderService;

@Service
public class LenderServiceImpl implements LenderService {

	@Autowired
	LenderServiceRepository lenderServiceRepository;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public String createLender(Lender lender) {
		logger.info("Creating Lender: {}", lender);
		
		List<Lender> existingLender = lenderServiceRepository.findByName(lender.getName());
		if (existingLender != null && !existingLender.isEmpty()) {
			throw new LenderAlreadyExistsException("Lender with name '" + lender.getName() + "' already exists");
		}
		lender.setStatus(LenderStatus.PENDING);
		lender.setCreatedDate(Calendar.getInstance().getTime());
		Lender lenderCreated = lenderServiceRepository.save(lender);
		return lenderCreated.getId();
	}

	@Override
	public Lender getLenderById(String lenderId) {
		Lender lender = lenderServiceRepository.findByLenderId(lenderId);
		if(lender == null) {
			throw new LenderNotFoundException("Lender " + lenderId + " Not Found");
		}
		return lender;
	}

	@Override
	public List<Lender> getAllLenders() {
		return lenderServiceRepository.findAll();
	}

	@Override
	public void updateLender(Lender lender) {
		lender.setUpdatedDate(Calendar.getInstance().getTime());
		lenderServiceRepository.save(lender);
	}

	@Override
	public void deleteLenderById(String lenderId) {
		lenderServiceRepository.deleteById(lenderId);
	}

	@Override
	public void updateLenderStatus(String lenderId, LenderStatus status) {
		Lender lender = lenderServiceRepository.findByLenderId(lenderId);
		lender.setStatus(status);
		lender.setUpdatedDate(Calendar.getInstance().getTime());
		lenderServiceRepository.save(lender);
	}

}
