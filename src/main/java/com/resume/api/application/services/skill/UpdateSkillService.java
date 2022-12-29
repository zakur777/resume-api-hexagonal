package com.resume.api.application.services.skill;

import com.resume.api.application.domains.entities.Skill;
import com.resume.api.application.ports.in.skill.UpdateSkillUseCase;
import com.resume.api.application.ports.out.skill.SkillGateway;
import com.resume.api.application.services.exceptions.skill.SkillNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UpdateSkillService implements UpdateSkillUseCase {

  private final SkillGateway gateway;

  public UpdateSkillService(SkillGateway gateway) {
    this.gateway = gateway;
  }

  @Override
  public Skill execute(Skill skill) throws Exception {
    if (gateway.getSkill(skill.getId()).isPresent()) {
      return gateway.updateSkill(skill);
    }
    Object[] args = {skill.getId()};
    throw new SkillNotFoundException("skillNotFound", args);
  }
}
