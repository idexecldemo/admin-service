package com.idexcel.adminservice.dto;

import java.util.Calendar;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ErrorResponseDTO {
	private String message;
	private String service = "admin service";
	private long timestamp = Calendar.getInstance().getTimeInMillis();
	
	public ErrorResponseDTO(String message) {
		this.message = message;
	}
}
