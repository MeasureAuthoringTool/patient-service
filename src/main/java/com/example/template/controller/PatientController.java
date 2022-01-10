package com.example.template.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.template.models.Patient;
import com.example.template.repositories.PatientRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PatientController {

  @Autowired private final PatientRepository repository;

  @RequestMapping(method = RequestMethod.GET)
  public ResponseEntity<Object> health() {
    return new ResponseEntity<>("PatientController is running...", HttpStatus.OK);
  }

  @PostMapping("/patient")
  public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
    patient.setId(null);
    Patient savePatient = repository.save(patient);

    return ResponseEntity.status(HttpStatus.CREATED).body(savePatient);
  }
}
