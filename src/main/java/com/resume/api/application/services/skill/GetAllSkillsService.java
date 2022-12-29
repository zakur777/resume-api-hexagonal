package com.resume.api.application.services.skill;

import com.resume.api.application.domains.entities.Skill;
import com.resume.api.application.ports.in.skill.GetAllSkillsUseCase;
import com.resume.api.application.ports.out.skill.SkillGateway;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class GetAllSkillsService implements GetAllSkillsUseCase {

  private final SkillGateway gateway;

  public GetAllSkillsService(SkillGateway gateway) {
    this.gateway = gateway;
  }

  @Override
  public List<Skill> execute(Void unused) throws Exception {
    return gateway.getAllSkills();
  }
}
