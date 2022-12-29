package com.resume.api.infrastructure.mappers.skill;

import com.resume.api.application.domains.entities.Skill;
import com.resume.api.infrastructure.models.SkillDAO;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface SkillMapper {
  @Mappings({
    @Mapping(source = "id", target = "id"),
    @Mapping(source = "name", target = "name"),
    @Mapping(source = "level", target = "level"),
    @Mapping(source = "resumeId", target = "resumeId"),
    @Mapping(source = "status", target = "status"),
  })
  Skill toSkill(SkillDAO skillDAO);

  List<Skill> toSkills(List<SkillDAO> skillDAOS);

  @InheritInverseConfiguration
  @Mappings({
    @Mapping(target = "createAt", ignore = true),
    @Mapping(target = "updateAt", ignore = true)
  })
  SkillDAO toSkillDAO(Skill skill);
}
