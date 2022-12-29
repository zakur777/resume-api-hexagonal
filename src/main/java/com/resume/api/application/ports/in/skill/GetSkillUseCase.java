package com.resume.api.application.ports.in.skill;

import com.resume.api.application.domains.entities.Skill;
import com.resume.api.commons.UseCase;
import java.util.Optional;

public interface GetSkillUseCase extends UseCase<Long, Optional<Skill>> {}
