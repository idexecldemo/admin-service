package com.idexcel.adminservice.dto;

import java.util.Calendar;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ErrorResponse {
	private String message;
	private final String service = "admin service";
	private final long timestamp = Calendar.getInstance().getTimeInMillis();
	
	public ErrorResponse(String message) {
		this.message = message;
	}
}
