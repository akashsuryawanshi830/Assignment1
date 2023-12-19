package com.skytech.lead.response;

import java.util.List;

import com.skytech.lead.entity.Lead;

import lombok.AllArgsConstructor;
import lombok.Data;


public class ApiResponse {
	private String status;
    private String data;
	private List<Lead> leads;
	
    public ApiResponse(String status, String data) {
		super();
		this.status = status;
		this.data = data;
	}

	public ApiResponse(String status2, List<Lead> leads) {
		this.status= status2;
		this.leads=leads;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public List<Lead> getLeads() {
		return leads;
	}

	public void setLeads(List<Lead> leads) {
		this.leads = leads;
	}
    
    
    
    
}
