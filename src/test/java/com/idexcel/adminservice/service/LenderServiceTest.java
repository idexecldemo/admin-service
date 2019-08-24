package com.idexcel.adminservice.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import com.idexcel.adminservice.dao.LenderServiceRepository;
import com.idexcel.adminservice.entity.Lender;
import com.idexcel.adminservice.exceptions.LenderAlreadyExistsException;
import com.idexcel.adminservice.service.impl.LenderServiceImpl;

@RunWith(SpringRunner.class)
public class LenderServiceTest {

	@InjectMocks
	private LenderServiceImpl lenderService;
	
	@Mock
	LenderServiceRepository lenderServiceRepository;
	
	@Test
	public void createLender( ) {
		Lender lender = getLender();
		List<Lender> list = new ArrayList<>();
		list.add(lender);
		when(lenderServiceRepository.findByName(any())).thenReturn(list);
		when(lenderServiceRepository.save(any())).thenReturn(lender);
		try {
			lenderService.createLender(lender);
			fail("Lender Create Should fail because lender already exists");
		} catch (LenderAlreadyExistsException e) {
			
		}
		
	}
	
	@Test
	public void createLenderFailLenderAlreadyExists( ) {
		Lender lender = getLender();
		when(lenderServiceRepository.findByName(any())).thenReturn(null);
		when(lenderServiceRepository.save(any())).thenReturn(lender);
		String lenderCreated = lenderService.createLender(lender);
		assertEquals("123", lenderCreated);
	}
	
	private Lender getLender( ) {
		Lender lender = new Lender();
		lender.setName("JUnit Lender");
		lender.setId("123");
		return lender;
	}
}
