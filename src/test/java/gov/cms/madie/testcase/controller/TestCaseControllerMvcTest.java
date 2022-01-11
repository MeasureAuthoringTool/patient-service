package gov.cms.madie.testcase.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import com.fasterxml.jackson.databind.ObjectMapper;

import gov.cms.madie.testcase.models.TestCase;
import gov.cms.madie.testcase.repositories.TestCaseRepository;

@WebMvcTest({TestCaseController.class})
public class TestCaseControllerMvcTest {

  @MockBean private TestCaseRepository repository;
  @Autowired private MockMvc mockMvc;
  @Captor private ArgumentCaptor<TestCase> patientArgumentCaptor;

  private TestCase testCase;
  private static final String TEST_ID = "TESTID";
  private static final String TEST_USER = "TestUser";
  private static final String TEST_USER_2 = "TestUser2";

  @BeforeEach
  public void setUp() {
    testCase = new TestCase();
    testCase.setId(TEST_ID);
    testCase.setCreatedBy(TEST_USER);
    testCase.setLastModifiedBy(TEST_USER_2);
  }

  @Test
  public void testNewTestCasePassed() throws Exception {
    when(repository.save(any(TestCase.class))).thenReturn(testCase);

    mockMvc
        .perform(
            MockMvcRequestBuilders.post(ControllerUtil.TEST_CASE)
                .content(asJsonString(testCase))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(MockMvcResultMatchers.status().isCreated())
        .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(TEST_ID))
        .andExpect(MockMvcResultMatchers.jsonPath("$.createdBy").value(TEST_USER))
        .andExpect(MockMvcResultMatchers.jsonPath("$.lastModifiedBy").value(TEST_USER_2));
  }

  private String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
