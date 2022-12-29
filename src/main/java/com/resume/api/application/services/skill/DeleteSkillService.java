package com.resume.api.application.services.skill;

import com.resume.api.application.domains.entities.Skill;
import com.resume.api.application.ports.in.skill.DeleteSkillUseCase;
import com.resume.api.application.ports.out.skill.SkillGateway;
import com.resume.api.application.services.exceptions.skill.SkillNotFoundException;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class DeleteSkillService implements DeleteSkillUseCase {

  private final SkillGateway gateway;

  public DeleteSkillService(SkillGateway gateway) {
    this.gateway = gateway;
  }

  @Override
  public Boolean execute(Skill skill) throws Exception {
    Optional<Skill> skillById = gateway.getSkill(skill.getId());
    if (skillById.isPresent()) {
      skill.setStatus("0");
      return gateway.deleteSkill(skill);
    }
    Object[] args = {skill.getId()};
    throw new SkillNotFoundException("skillNotFound", args);
  }
}
