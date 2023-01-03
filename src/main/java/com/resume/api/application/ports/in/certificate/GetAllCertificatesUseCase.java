package com.resume.api.application.ports.in.certificate;

import com.resume.api.application.domains.entities.Certificate;
import com.resume.api.commons.UseCase;
import java.util.List;

public interface GetAllCertificatesUseCase extends UseCase<Void, List<Certificate>> {}
