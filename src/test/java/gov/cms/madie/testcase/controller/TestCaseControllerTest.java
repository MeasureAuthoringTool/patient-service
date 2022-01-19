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

import java.util.List;
import java.util.Objects;

@ExtendWith(MockitoExtension.class)
public class TestCaseControllerTest {
  @Mock private TestCaseRepository repository;

  @InjectMocks private TestCaseController controller;

  private TestCase testCase;

  @BeforeEach
  public void setUp() {
    testCase = new TestCase();
    testCase.setId("TESTID");
    testCase.setName("IPPPass");
    testCase.setSeries("BloodPressure>124");
    testCase.setCreatedBy("TestUser");
    testCase.setLastModifiedBy("TestUser2");
  }

  @Test
  void saveTestCase() {
    Mockito.doReturn(testCase).when(repository).save(ArgumentMatchers.any());

    TestCase saveTestCase = new TestCase();

    ResponseEntity<TestCase> response = controller.addTestCase(saveTestCase);
    assertEquals("TESTID", response.getBody().getId());
  }

  @Test
  void getTestCases() {
    Mockito.doReturn(List.of(testCase)).when(repository).findAll();

    ResponseEntity<List<TestCase>> response = controller.getTestCases();
    assertEquals(1, Objects.requireNonNull(response.getBody()).size());
    assertEquals("IPPPass", response.getBody().get(0).getName());
    assertEquals("BloodPressure>124", response.getBody().get(0).getSeries());
  }
}
