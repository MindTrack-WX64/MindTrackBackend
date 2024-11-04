package com.mindtrack.backend.profiles.application.acl;

import com.mindtrack.backend.profiles.domain.model.commands.CreatePatientCommand;
import com.mindtrack.backend.profiles.domain.model.commands.CreateProfessionalCommand;
import com.mindtrack.backend.profiles.domain.model.queries.GetPatientByIdQuery;
import com.mindtrack.backend.profiles.domain.model.queries.GetProfessionalByIdQuery;
import com.mindtrack.backend.profiles.domain.services.PatientCommandService;
import com.mindtrack.backend.profiles.domain.services.PatientQueryService;
import com.mindtrack.backend.profiles.domain.services.ProfessionalCommandService;
import com.mindtrack.backend.profiles.domain.services.ProfessionalQueryService;
import com.mindtrack.backend.profiles.interfaces.acl.ProfilesContextFacade;
import org.springframework.stereotype.Service;

@Service
public class ProfilesContextFacadeImpl implements ProfilesContextFacade {

    private final PatientCommandService patientCommandService;
    private final PatientQueryService patientQueryService;
    private final ProfessionalCommandService professionalCommandService;
    private final ProfessionalQueryService professionalQueryService;

    public ProfilesContextFacadeImpl(PatientCommandService patientCommandService, PatientQueryService patientQueryService, ProfessionalCommandService professionalCommandService, ProfessionalQueryService professionalQueryService) {
        this.patientCommandService = patientCommandService;
        this.patientQueryService = patientQueryService;
        this.professionalCommandService = professionalCommandService;
        this.professionalQueryService = professionalQueryService;
    }

    @Override
    public Long createPatient(String fullName, String email, String phone, String birthDate, Long userId) {
        var createPatientCommand = new CreatePatientCommand(
                fullName,
                email,
                phone,
                birthDate,
                userId
        );
        var patient = patientCommandService.handle(createPatientCommand);
        return patient.isEmpty() ? Long.valueOf(0L) : patient.get().getId();
    }

    @Override
    public Long createProfessional(String fullName, String email, String phone, String birthDate, String professionalType, Long userId) {
        var createProfessionalCommand = new CreateProfessionalCommand(
                fullName,
                email,
                phone,
                birthDate,
                professionalType,
                userId
        );
        var professional = professionalCommandService.handle(createProfessionalCommand);
        return professional.isEmpty() ? Long.valueOf(0L) : professional.get().getId();
    }

    @Override
    public Long fetchProfileByPatientId(Long patientId) {
        var getPatientById = new GetPatientByIdQuery(patientId);
        var patient = patientQueryService.handle(getPatientById);
        return patient.isEmpty() ? Long.valueOf(0L) : patient.get().getId();
    }

    @Override
    public Long fetchProfileByProfessionalId(Long professionalId) {
        var getProfessionalById = new GetProfessionalByIdQuery(professionalId);
        var professional = professionalQueryService.handle(getProfessionalById);
        return professional.isEmpty() ? Long.valueOf(0L) : professional.get().getId();
    }
}
