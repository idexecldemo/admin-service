package com.idexcel.adminservice.service;

import java.util.List;

import com.idexcel.adminservice.entity.Lender;
import com.idexcel.adminservice.enums.LenderStatus;

public interface  LenderService {

	public String createLender(Lender lender); 
	
	public Lender getLenderById(String lenderId);
	
	public List<Lender> getAllLenders();
	
	public void updateLender(Lender lender);
	
	public void deleteLenderById(String lenderId);
	
	public void updateLenderStatus(String lenderId, LenderStatus status);
}
