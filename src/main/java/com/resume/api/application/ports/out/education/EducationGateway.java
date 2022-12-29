package com.resume.api.application.ports.out.education;

import com.resume.api.application.domains.entities.Education;
import java.util.List;
import java.util.Optional;

public interface EducationGateway {

  Education createEducation(Education education);

  Education updateEducation(Education education);

  Boolean deleteEducation(Education education);

  Optional<Education> getEducation(Long id);

  List<Education> getAllEducations();
}
