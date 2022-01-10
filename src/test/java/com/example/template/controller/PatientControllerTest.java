package com.example.template.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.example.template.models.Patient;
import com.example.template.repositories.PatientRepository;

@ExtendWith(MockitoExtension.class)
public class PatientControllerTest {
  @Mock private PatientRepository repository;

  @InjectMocks private PatientController controller;

  private Patient patient;

  @BeforeEach
  public void setUp() {
    patient = new Patient();
    patient.setId("TESTID");
    patient.setCreatedBy("TestUser");
    patient.setLastModifiedBy("TestUser2");
  }

  @Test
  void savePatient() {
    Mockito.doReturn(patient).when(repository).save(ArgumentMatchers.any());

    Patient savePatient = new Patient();

    ResponseEntity<Patient> response = controller.addPatient(savePatient);
    assertEquals("TESTID", response.getBody().getId());
  }
}
