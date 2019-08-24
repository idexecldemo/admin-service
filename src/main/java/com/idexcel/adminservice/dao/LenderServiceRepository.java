package com.idexcel.adminservice.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import com.idexcel.adminservice.entity.Lender;

public interface LenderServiceRepository extends MongoRepository<Lender, String> {
	
	@Query("{name : ?0}")
	public List<Lender> findByName(String lenderName);

}
