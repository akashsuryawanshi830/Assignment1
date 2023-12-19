package com.skytech.lead.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.skytech.lead.entity.Lead;
import com.skytech.lead.exception.LeadAlreadyExistsException;
import com.skytech.lead.response.ApiError;
import com.skytech.lead.response.ApiResponse;
import com.skytech.lead.response.ErrorResponse;
import com.skytech.lead.service.LeadService;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

	 @Autowired
	    private LeadService leadService;

	    @PostMapping
	    public ResponseEntity<?> createLead(@RequestBody Lead lead) {
	        try {
	            String result = leadService.createLead(lead);
	            return new ResponseEntity<>(new ApiResponse("success", result), HttpStatus.OK);
	        } catch (LeadAlreadyExistsException e) {
	            ErrorResponse errorResponse = new ErrorResponse("E10010", e.getMessage());
	            return new ResponseEntity<>(new ApiError("error", errorResponse), HttpStatus.BAD_REQUEST);
	        }
	    }
	    
	    @GetMapping("/byMobileNumber")
	    public ResponseEntity<?> getLeadsByMobileNumber(@RequestParam String mobileNumber) {
	        List<Lead> leads = leadService.getLeadsByMobileNumber(mobileNumber);

	        if (leads.isEmpty()) {
	            ErrorResponse errorResponse = new ErrorResponse("E10011", "No Lead found with the Mobile Number " + mobileNumber);
	            return new ResponseEntity<>(new ApiError("error", errorResponse), HttpStatus.NOT_FOUND);
	        }

	        return new ResponseEntity<>(new ApiResponse("success", leads), HttpStatus.OK);
	    }
}
