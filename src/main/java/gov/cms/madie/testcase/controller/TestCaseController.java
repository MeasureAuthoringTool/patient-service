package gov.cms.madie.testcase.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import gov.cms.madie.testcase.models.TestCase;
import gov.cms.madie.testcase.repositories.TestCaseRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TestCaseController {

  @Autowired private final TestCaseRepository repository;

  @PostMapping(ControllerUtil.TEST_CASE)
  public ResponseEntity<TestCase> addTestCase(@RequestBody TestCase testCase) {
    testCase.setId(null);
    TestCase saveTestCase = repository.save(testCase);

    return ResponseEntity.status(HttpStatus.CREATED).body(saveTestCase);
  }

  @GetMapping(ControllerUtil.TEST_CASES)
  public ResponseEntity<List<TestCase>> getTestCases() {
    return ResponseEntity.ok(repository.findAll());
  }
}
