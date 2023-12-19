package com.skytech.lead.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.skytech.lead.entity.Lead;
import com.skytech.lead.exception.LeadAlreadyExistsException;
import com.skytech.lead.repository.LeadRepository;

@Service
public class LeadService {
	
	 @Autowired
	 private LeadRepository leadRepository;
	 
	 public String createLead(Lead lead) {
	        if (leadRepository.existsByLeadId(lead.getLeadId())) {
	            throw new LeadAlreadyExistsException("Lead Already Exists in the database with the lead id");
	        }

	        if (leadRepository.existsByEmail(lead.getEmail())) {
	            throw new LeadAlreadyExistsException("Lead Already Exists in the database with the email");
	        }
	        
	leadRepository.save(lead);
	return "Created Leads Successfully";

	 }
	 
	 public List<Lead> getLeadsByMobileNumber(String mobileNumber) {
	        return leadRepository.findByMobileNumber(mobileNumber);
	    }
}
