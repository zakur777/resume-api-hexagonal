package com.resume.api.infrastructure.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "educations")
public class EducationDAO extends GenericEducationDAO {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;


  /*@ManyToOne
  @JoinColumn(name = "resume_id", insertable = false, updatable = false)
  private ResumeDAO resume;*/
}
