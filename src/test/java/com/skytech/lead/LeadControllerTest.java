package com.skytech.lead;

//LeadControllerTest.java
import com.fasterxml.jackson.databind.ObjectMapper;
import com.skytech.lead.controller.LeadController;
import com.skytech.lead.entity.Lead;
import com.skytech.lead.exception.LeadAlreadyExistsException;
import com.skytech.lead.service.LeadService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Date;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class LeadControllerTest {
 @Mock
 private LeadService leadService;

 @InjectMocks
 private LeadController leadController;

 private final ObjectMapper objectMapper = new ObjectMapper();

 private final MockMvc mockMvc = MockMvcBuilders.standaloneSetup(leadController).build();

 @Test
 public void testCreateLead_Success() throws Exception {
     Lead lead = new Lead();
     lead.setLeadId(5678L);
     lead.setFirstName("Vineet");
     lead.setLastName("KV");
     lead.setMobileNumber("8877887788");
     lead.setGender("Male");
     lead.setDob(new Date());
     lead.setEmail("v@gmail.com");

     when(leadService.createLead(any(Lead.class))).thenReturn("Created Leads Successfully");

     mockMvc.perform(post("/api/leads")
             .contentType(MediaType.APPLICATION_JSON)
             .content(objectMapper.writeValueAsString(lead)))
             .andExpect(status().isOk())
             .andExpect(jsonPath("$.status").value("success"))
             .andExpect(jsonPath("$.data").value("Created Leads Successfully"));
 }

 @Test
 public void testCreateLead_AlreadyExists() throws Exception {
     Lead lead = new Lead();
     lead.setLeadId(5678L);
     lead.setFirstName("Vineet");
     lead.setLastName("KV");
     lead.setMobileNumber("8877887788");
     lead.setGender("Male");
     lead.setDob(new Date());
     lead.setEmail("v@gmail.com");

     when(leadService.createLead(any(Lead.class)))
             .thenThrow(new LeadAlreadyExistsException("Lead Already Exists in the database with the lead id"));

     mockMvc.perform(post("/api/leads")
             .contentType(MediaType.APPLICATION_JSON)
             .content(objectMapper.writeValueAsString(lead)))
             .andExpect(status().isBadRequest())
             .andExpect(jsonPath("$.status").value("error"))
             .andExpect(jsonPath("$.errorResponse.code").value("E10010"))
             .andExpect(jsonPath("$.errorResponse.messages[0]")
                     .value("Lead Already Exists in the database with the lead id"));
 }
}

