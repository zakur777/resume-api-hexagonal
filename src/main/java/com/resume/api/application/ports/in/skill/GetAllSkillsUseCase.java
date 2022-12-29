package com.resume.api.application.ports.in.skill;

import com.resume.api.application.domains.entities.Skill;
import com.resume.api.commons.UseCase;
import java.util.List;

public interface GetAllSkillsUseCase extends UseCase<Void, List<Skill>> {}
