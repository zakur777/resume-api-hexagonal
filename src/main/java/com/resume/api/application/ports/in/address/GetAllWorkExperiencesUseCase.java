package com.resume.api.application.ports.in.address;

import com.resume.api.application.domains.entities.WorkExperience;
import com.resume.api.commons.UseCase;
import java.util.List;

public interface GetAllWorkExperiencesUseCase extends UseCase<Void, List<WorkExperience>> {}
