package com.resume.api.infrastructure.adapters.out.workexperience;

import com.resume.api.application.domains.entities.WorkExperience;
import com.resume.api.application.ports.out.workexperience.WorkExperienceGateway;
import com.resume.api.infrastructure.crud.workexperience.WorkExperienceCrudRepository;
import com.resume.api.infrastructure.mappers.workexperience.WorkExperienceMapper;
import com.resume.api.infrastructure.models.WorkExperienceDAO;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Transactional
@Repository
public class WorkExperienceRepository implements WorkExperienceGateway {

  private final WorkExperienceCrudRepository workExperienceCrudRepository;
  private final WorkExperienceMapper mapper;

  @Override
  public WorkExperience createWorkExperience(WorkExperience workExperience) {
    WorkExperienceDAO workExperienceDAO = mapper.toWorkExperienceDAO(workExperience);
    return mapper.toWorkExperience(workExperienceCrudRepository.save(workExperienceDAO));
  }

  @Override
  public WorkExperience updateWorkExperience(WorkExperience workExperience) {
    WorkExperienceDAO workExperienceDAO = mapper.toWorkExperienceDAO(workExperience);
    return mapper.toWorkExperience(workExperienceCrudRepository.save(workExperienceDAO));
  }

  @Override
  public Boolean deleteWorkExperience(WorkExperience workExperience) {
    if (getWorkExperience(workExperience.getId()).isPresent()) {
      WorkExperienceDAO workExperienceDAO = mapper.toWorkExperienceDAO(workExperience);
      workExperienceCrudRepository.save(workExperienceDAO);
      return true;
    }
    return false;
  }

  @Override
  public Optional<WorkExperience> getWorkExperience(Long id) {
    return workExperienceCrudRepository
        .findById(id)
        .map(workExperience -> mapper.toWorkExperience(workExperience));
  }

  @Override
  public List<WorkExperience> getAllWorkExperiences() {
    List<WorkExperienceDAO> allWorkExperienceDaos =
        workExperienceCrudRepository.getAllWorkExperiences();
    return mapper.toWorkExperiences(allWorkExperienceDaos);
  }
}
