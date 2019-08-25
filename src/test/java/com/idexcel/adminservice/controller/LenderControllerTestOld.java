package com.idexcel.adminservice.controller;

import static org.mockito.BDDMockito.any;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idexcel.adminservice.AdminServiceApplication;
import com.idexcel.adminservice.AdminServiceConfig;
import com.idexcel.adminservice.dto.LenderDTO;
import com.idexcel.adminservice.entity.Lender;
import com.idexcel.adminservice.service.LenderService;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AdminServiceApplication.class)
@WebAppConfiguration
public class LenderControllerTestOld {

	protected MockMvc mockMvc;
	
	@Autowired
	WebApplicationContext webApplicationContext;
	
	@MockBean
	LenderService lenderService;
	
	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void lenderCreateTest() throws Exception {
		
		when(lenderService.createLender(any(Lender.class))).thenReturn("DummyLenderId");
		mockMvc.perform( MockMvcRequestBuilders
				.post("/lenders")
				.content(convertObjectToJson(getLender()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void test() throws Exception {
		mockMvc.perform( MockMvcRequestBuilders
				.get("/lenders/test")).andExpect(status().isOk());
	}
	
	private static String convertObjectToJson(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	private static LenderDTO getLender() {
		LenderDTO lenderDto = new LenderDTO();
		lenderDto.setName("JUnit Lender");
		return lenderDto;
	}
}
