package com.resume.api.application.ports.in.address;

import com.resume.api.application.domains.entities.WorkExperience;
import com.resume.api.commons.UseCase;
import java.util.Optional;

public interface GetWorkExperienceUseCase extends UseCase<Long, Optional<WorkExperience>> {}
