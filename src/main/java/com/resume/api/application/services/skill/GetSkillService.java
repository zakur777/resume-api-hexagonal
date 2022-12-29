package com.resume.api.application.services.skill;

import com.resume.api.application.domains.entities.Skill;
import com.resume.api.application.ports.in.skill.GetSkillUseCase;
import com.resume.api.application.ports.out.skill.SkillGateway;
import com.resume.api.application.services.exceptions.skill.SkillNotFoundException;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class GetSkillService implements GetSkillUseCase {

  private final SkillGateway gateway;

  public GetSkillService(SkillGateway gateway) {
    this.gateway = gateway;
  }

  @Override
  public Optional<Skill> execute(Long id) throws Exception {
    Optional<Skill> skill = gateway.getSkill(id);
    if (skill.isPresent()) {
      return skill;
    }
    Object[] args = {id};
    throw new SkillNotFoundException("skillNotFound", args);
  }
}
