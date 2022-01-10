package com.example.template.models;

import java.sql.Date;

import org.springframework.data.annotation.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Patient {
  @Id private String id;
  private Date createdAt;
  private String createdBy;
  private Date lastModifiedAt;
  private String lastModifiedBy;
}
