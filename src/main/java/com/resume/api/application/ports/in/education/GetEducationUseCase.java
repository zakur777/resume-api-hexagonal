package com.resume.api.application.ports.in.education;

import com.resume.api.application.domains.entities.Education;
import com.resume.api.commons.UseCase;
import java.util.Optional;

public interface GetEducationUseCase extends UseCase<Long, Optional<Education>> {}
