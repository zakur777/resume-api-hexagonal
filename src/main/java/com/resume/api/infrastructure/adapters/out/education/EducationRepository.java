package com.resume.api.infrastructure.adapters.out.education;

import com.resume.api.application.domains.entities.Education;
import com.resume.api.application.ports.out.education.EducationGateway;
import com.resume.api.infrastructure.crud.education.EducationCrudRepository;
import com.resume.api.infrastructure.mappers.education.EducationMapper;
import com.resume.api.infrastructure.models.EducationDAO;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Transactional
@Repository
public class EducationRepository implements EducationGateway {

  private final EducationCrudRepository educationCrudRepository;
  private final EducationMapper mapper;

  @Override
  public Education createEducation(Education education) {
    EducationDAO educationDAO = mapper.toEducationDAO(education);
    return mapper.toEducation(educationCrudRepository.save(educationDAO));
  }

  @Override
  public Education updateEducation(Education education) {
    EducationDAO educationDAO = mapper.toEducationDAO(education);
    return mapper.toEducation(educationCrudRepository.save(educationDAO));
  }

  @Override
  public Boolean deleteEducation(Education education) {
    if (getEducation(education.getId()).isPresent()) {
      EducationDAO educationDAO = mapper.toEducationDAO(education);
      educationCrudRepository.save(educationDAO);
      return true;
    }
    return false;
  }

  @Override
  public Optional<Education> getEducation(Long id) {
    return educationCrudRepository
        .findById(id)
        .map(education -> mapper.toEducation(education));
  }

  @Override
  public List<Education> getAllEducations() {
    List<EducationDAO> allEducationDaos =
        educationCrudRepository.getAllEducations();
    return mapper.toEducations(allEducationDaos);
  }
}
