package com.idexcel.adminservice.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.idexcel.adminservice.enums.LenderStatus;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document (collection = "lender")
@Getter @Setter @ToString
public class Lender {

	@Id
	private String id;
	
	private String name;
	
	private LenderStatus status;
	
	private Date createdDate;
	
	private String createdBy;
	
	private Date updatedDate;
	
	private String updatedBy;
	
	private Address address;
}
