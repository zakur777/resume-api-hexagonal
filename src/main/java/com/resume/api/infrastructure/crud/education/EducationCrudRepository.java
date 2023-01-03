package com.resume.api.infrastructure.crud.education;

import com.resume.api.infrastructure.models.EducationDAO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EducationCrudRepository extends JpaRepository<EducationDAO, Long> {
  @Query("select e from EducationDAO e where e.status='1'")
  List<EducationDAO> getAllEducations();
}
