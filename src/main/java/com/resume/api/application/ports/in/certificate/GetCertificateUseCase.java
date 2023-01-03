package com.resume.api.application.ports.in.certificate;

import com.resume.api.application.domains.entities.Certificate;
import com.resume.api.commons.UseCase;
import java.util.Optional;

public interface GetCertificateUseCase extends UseCase<Long, Optional<Certificate>> {}
