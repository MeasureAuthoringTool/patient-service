package gov.cms.madie.testcase.models;

import java.sql.Date;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TestCase {
  @Id private String id;
  private String name;
  private String series;
  private String description;
  private Date createdAt;
  private String createdBy;
  private Date lastModifiedAt;
  private String lastModifiedBy;
}
