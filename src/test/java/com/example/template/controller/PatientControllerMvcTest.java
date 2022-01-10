package com.example.template.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.example.template.models.Patient;
import com.example.template.repositories.PatientRepository;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest({PatientController.class})
public class PatientControllerMvcTest {

  @MockBean private PatientRepository repository;
  @Autowired private MockMvc mockMvc;
  @Captor private ArgumentCaptor<Patient> patientArgumentCaptor;
  
  private Patient patient;
  
  
  @BeforeEach
  public void setUp() {
    patient = new Patient();
    patient.setId("TESTID");
    patient.setCreatedBy("TestUser");
    patient.setLastModifiedBy("TestUser2");
  }

  @Test
  public void testHealth() throws Exception {
    this.mockMvc
      .perform(MockMvcRequestBuilders.get("/"))
        .andExpect(MockMvcResultMatchers.status().isOk())
        	.andExpect(MockMvcResultMatchers.content().string("PatientController is running..."));
  }
  
  @Test
  public void testNewPatientPassed() throws Exception {
  	when(repository.save(any(Patient.class))).thenReturn(patient);
  	
  	mockMvc.perform( MockMvcRequestBuilders
	      .post("/patient")
	      	.content(asJsonString(patient))
	      		.contentType(MediaType.APPLICATION_JSON)
	      			.accept(MediaType.APPLICATION_JSON))
								.andDo(print())
									.andExpect(MockMvcResultMatchers.status().isCreated());	
  	
  	verify(repository, times(1)).save(patientArgumentCaptor.capture());
  	verifyNoMoreInteractions(repository);
  	Patient savePatient = patientArgumentCaptor.getValue();
  	Assertions.assertEquals("TESTID", savePatient.getId());
  	Assertions.assertEquals("TestUser", savePatient.getCreatedBy());
  	Assertions.assertEquals("TestUser2", savePatient.getLastModifiedBy());
  }
  
  private String asJsonString(final Object obj) {
    try {
        return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
        throw new RuntimeException(e);
    }
	}
}
