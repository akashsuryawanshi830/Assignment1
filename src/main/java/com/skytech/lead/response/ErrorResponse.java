package com.skytech.lead.response;

import lombok.AllArgsConstructor;
import lombok.Data;


public class ErrorResponse {
	private String code;
    private String messages;
	
    public ErrorResponse(String code, String string) {
		this.code = code;
		this.messages = string;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMessages() {
		return messages;
	}
	public void setMessages(String messages) {
		this.messages = messages;
	}
    
    
}
