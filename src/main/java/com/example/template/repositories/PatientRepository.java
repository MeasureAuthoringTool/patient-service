package com.example.template.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.template.models.Patient;

public interface PatientRepository extends MongoRepository<Patient, String> {}
