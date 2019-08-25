package com.idexcel.adminservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.idexcel.adminservice.dto.LenderDTO;
import com.idexcel.adminservice.entity.Lender;
import com.idexcel.adminservice.exceptions.LenderAlreadyExistsException;
import com.idexcel.adminservice.exceptions.LenderNotFoundException;
import com.idexcel.adminservice.service.LenderService;

@RunWith(SpringRunner.class)
@WebMvcTest({LenderController.class})

public class LenderControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	LenderService lenderService;
	
	@MockBean
	ModelMapper modelMapper;
	
	@Test
	public void lenderCreateTest() throws Exception {
		when(modelMapper.map(any(), any())).thenReturn(new Lender());
		when(lenderService.createLender(any(Lender.class))).thenReturn("DummyLenderId");
		mockMvc.perform( MockMvcRequestBuilders
				.post("/idexceldemo/lenders")
				.content(convertObjectToJson(getLender()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
		verify(modelMapper, atLeast(1)).map(any(), any());
		verify(lenderService, atLeast(1)).createLender(any(Lender.class));
	}
	
	@Test
	public void lenderCreateFailLenderAlreadyExistsTest() throws Exception {
		when(modelMapper.map(any(), any())).thenReturn(new Lender());
		when(lenderService.createLender(any(Lender.class))).thenThrow(LenderAlreadyExistsException.class);
		mockMvc.perform( MockMvcRequestBuilders
				.post("/idexceldemo/lenders")
				.content(convertObjectToJson(getLender()))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isConflict());
	}
	
	@Test
	public void getLenderById() throws Exception {
		when(lenderService.getLenderById(any())).thenReturn(new Lender());
		mockMvc.perform( MockMvcRequestBuilders
				.get("/idexceldemo/lenders/lenderId1")).andExpect(status().isOk());
	}
	
	@Test
	public void getLenderByInvalidId() throws Exception {
		when(lenderService.getLenderById(any())).thenThrow(LenderNotFoundException.class);
		mockMvc.perform( MockMvcRequestBuilders
				.get("/idexceldemo/lenders/lenderId1")).andExpect(status().isBadRequest());
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
