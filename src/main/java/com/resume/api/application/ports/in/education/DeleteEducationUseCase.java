package com.resume.api.application.ports.in.education;

import com.resume.api.application.domains.entities.Education;
import com.resume.api.commons.UseCase;

public interface DeleteEducationUseCase extends UseCase<Education, Boolean> {}