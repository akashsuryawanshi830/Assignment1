package com.skytech.lead.response;

import lombok.AllArgsConstructor;
import lombok.Data;


public class ApiError {
	private String status;
    private ErrorResponse errorResponse;
	
    
	public ApiError(String status, ErrorResponse errorResponse) {
		super();
		this.status = status;
		this.errorResponse = errorResponse;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public ErrorResponse getErrorResponse() {
		return errorResponse;
	}
	public void setErrorResponse(ErrorResponse errorResponse) {
		this.errorResponse = errorResponse;
	}
    
    
}
