package gov.cms.madie.testcase.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import gov.cms.madie.testcase.models.TestCase;

public interface TestCaseRepository extends MongoRepository<TestCase, String> {}
