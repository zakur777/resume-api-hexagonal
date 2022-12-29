package com.resume.api.infrastructure.adapters.out.skill;

import com.resume.api.application.domains.entities.Skill;
import com.resume.api.application.ports.out.skill.SkillGateway;
import com.resume.api.infrastructure.crud.skill.SkillCrudRepository;
import com.resume.api.infrastructure.mappers.skill.SkillMapper;
import com.resume.api.infrastructure.models.SkillDAO;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Transactional
@Repository
public class SkillRepository implements SkillGateway {

  private final SkillCrudRepository skillCrudRepository;
  private final SkillMapper mapper;

  @Override
  public Skill createSkill(Skill skill) {
    SkillDAO skillDAO = mapper.toSkillDAO(skill);
    return mapper.toSkill(skillCrudRepository.save(skillDAO));
  }

  @Override
  public Skill updateSkill(Skill skill) {
    SkillDAO skillDAO = mapper.toSkillDAO(skill);
    return mapper.toSkill(skillCrudRepository.save(skillDAO));
  }

  @Override
  public Boolean deleteSkill(Skill skill) {
    if (getSkill(skill.getId()).isPresent()) {
      SkillDAO skillDAO = mapper.toSkillDAO(skill);
      skillCrudRepository.save(skillDAO);
      return true;
    }
    return false;
  }

  @Override
  public Optional<Skill> getSkill(Long id) {
    return skillCrudRepository.findById(id).map(skill -> mapper.toSkill(skill));
  }

  @Override
  public List<Skill> getAllSkills() {
    List<SkillDAO> allSkillDaos = skillCrudRepository.getAllSkills();
    return mapper.toSkills(allSkillDaos);
  }
}
