package com.idexcel.adminservice.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class LenderDTO {
	private String id;
	private String name;
	private String status;
	private AddressDTO address;
	private Date createdDate;
	private String createdBy;
	private Date updatedDate;
	private String updatedBy;
	
}
