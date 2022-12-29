package com.resume.api.application.services.skill;

import com.resume.api.application.domains.entities.Skill;
import com.resume.api.application.ports.in.skill.CreateSkillUseCase;
import com.resume.api.application.ports.out.skill.SkillGateway;
import com.resume.api.application.services.exceptions.skill.DuplicateSkillException;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class CreateSkillService implements CreateSkillUseCase {

  private final SkillGateway gateway;

  public CreateSkillService(SkillGateway gateway) {
    this.gateway = gateway;
  }

  @Override
  public Skill execute(Skill skill) throws Exception {
    Optional<Skill> skillById = gateway.getSkill(skill.getId());
    if (skillById.isEmpty()) {
      return gateway.createSkill(skill);
    }
    Object[] args = {skill.getId()};
    throw new DuplicateSkillException("duplicateSkill", args);
  }
}
