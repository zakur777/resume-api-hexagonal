package com.resume.api.infrastructure.crud.skill;

import com.resume.api.infrastructure.models.SkillDAO;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface SkillCrudRepository extends JpaRepository<SkillDAO, Long> {
  @Query("select s from SkillDAO s where s.status='1'")
  List<SkillDAO> getAllSkills();
}
