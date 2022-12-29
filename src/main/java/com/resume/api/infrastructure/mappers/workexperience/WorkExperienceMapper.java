package com.resume.api.infrastructure.mappers.workexperience;

import com.resume.api.application.domains.entities.WorkExperience;
import com.resume.api.infrastructure.models.WorkExperienceDAO;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface WorkExperienceMapper {
  @Mappings({
    @Mapping(source = "id", target = "id"),
    @Mapping(source = "company", target = "company"),
    @Mapping(source = "position", target = "position"),
    @Mapping(source = "startPeriod", target = "startPeriod"),
    @Mapping(source = "endPeriod", target = "endPeriod"),
    @Mapping(source = "resumeId", target = "resumeId"),
    @Mapping(source = "status", target = "status"),
  })
  WorkExperience toWorkExperience(WorkExperienceDAO workExperienceDAO);

  List<WorkExperience> toWorkExperiences(List<WorkExperienceDAO> workExperienceDAOS);

  @InheritInverseConfiguration
  @Mappings({
    @Mapping(target = "createAt", ignore = true),
    @Mapping(target = "updateAt", ignore = true)
  })
  WorkExperienceDAO toWorkExperienceDAO(WorkExperience workExperience);
}
