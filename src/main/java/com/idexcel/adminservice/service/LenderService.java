package com.idexcel.adminservice.service;

import java.util.Optional;

import com.idexcel.adminservice.entity.Lender;

public interface  LenderService {

	public String createLender(Lender lender); 
	
	public Optional<Lender> getLender(String lenderId);
}
