package com.resume.api.infrastructure.crud.workexperience;

import com.resume.api.infrastructure.models.WorkExperienceDAO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface WorkExperienceCrudRepository extends JpaRepository<WorkExperienceDAO, Long> {
  @Query("select w from WorkExperienceDAO w where w.status='1'")
  List<WorkExperienceDAO> getAllWorkExperiences();
}
