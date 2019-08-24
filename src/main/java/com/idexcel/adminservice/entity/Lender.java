package com.idexcel.adminservice.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Document (collection = "lender")
@Getter @Setter @ToString
public class Lender {

	@Id
	private String id;
	
	private String name;
	
	private String status;
}
