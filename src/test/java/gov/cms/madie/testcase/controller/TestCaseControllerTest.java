package gov.cms.madie.testcase.controller;

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

import gov.cms.madie.testcase.models.TestCase;
import gov.cms.madie.testcase.repositories.TestCaseRepository;

@ExtendWith(MockitoExtension.class)
public class TestCaseControllerTest {
  @Mock private TestCaseRepository repository;

  @InjectMocks private TestCaseController controller;

  private TestCase testCase;

  @BeforeEach
  public void setUp() {
    testCase = new TestCase();
    testCase.setId("TESTID");
    testCase.setCreatedBy("TestUser");
    testCase.setLastModifiedBy("TestUser2");
  }

  @Test
  void savePatient() {
    Mockito.doReturn(testCase).when(repository).save(ArgumentMatchers.any());

    TestCase saveTestCase = new TestCase();

    ResponseEntity<TestCase> response = controller.addTestCase(saveTestCase);
    assertEquals("TESTID", response.getBody().getId());
  }
}
