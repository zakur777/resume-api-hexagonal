package com.resume.api.application.ports.out.skill;

import com.resume.api.application.domains.entities.Skill;
import java.util.List;
import java.util.Optional;

public interface SkillGateway {

  Skill createSkill(Skill skill);

  Skill updateSkill(Skill skill);

  Boolean deleteSkill(Skill skill);

  Optional<Skill> getSkill(Long id);

  List<Skill> getAllSkills();
}
