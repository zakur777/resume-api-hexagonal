package com.resume.api.application.ports.out.workexperience;

import com.resume.api.application.domains.entities.WorkExperience;
import java.util.List;
import java.util.Optional;

public interface WorkExperienceGateway {

  WorkExperience createWorkExperience(WorkExperience workExperience);

  WorkExperience updateWorkExperience(WorkExperience workExperience);

  Boolean deleteWorkExperience(WorkExperience workExperience);

  Optional<WorkExperience> getWorkExperience(Long id);

  List<WorkExperience> getAllWorkExperiences();
}
