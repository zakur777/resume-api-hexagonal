package com.resume.api.infrastructure.mappers.education;

import com.resume.api.application.domains.entities.Education;
import com.resume.api.infrastructure.models.EducationDAO;
import java.util.List;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface EducationMapper {
  @Mappings({
    @Mapping(source = "id", target = "id"),
    @Mapping(source = "institution", target = "institution"),
    @Mapping(source = "degree", target = "degree"),
    @Mapping(source = "description", target = "description"),
    @Mapping(source = "startDate", target = "startDate"),
    @Mapping(source = "endaDate", target = "endaDate"),
    @Mapping(source = "resumeId", target = "resumeId"),
    @Mapping(source = "status", target = "status")
  })
  Education toEducation(EducationDAO educationDAO);

  List<Education> toEducations(List<EducationDAO> educationDAOS);

  @InheritInverseConfiguration
  @Mappings({
    @Mapping(target = "createAt", ignore = true),
    @Mapping(target = "updateAt", ignore = true)
  })
  EducationDAO toEducationDAO(Education education);
}
