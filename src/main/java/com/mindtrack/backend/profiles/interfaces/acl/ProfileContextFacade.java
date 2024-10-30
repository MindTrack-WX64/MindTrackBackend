package com.mindtrack.backend.profiles.interfaces.acl;

import com.mindtrack.backend.profiles.domain.model.commands.CreatePatientCommand;
import com.mindtrack.backend.profiles.domain.model.commands.CreateProfessionalCommand;
import com.mindtrack.backend.profiles.domain.services.PatientCommandService;
import com.mindtrack.backend.profiles.domain.services.ProfessionalCommandService;
import com.mindtrack.backend.shared.domain.aggregates.AuditableAbstractAggregateRoot;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ProfileContextFacade {
    private final ProfessionalCommandService professionalCommandService;
    private final PatientCommandService patientCommandService;

    public ProfileContextFacade(ProfessionalCommandService professionalCommandService, PatientCommandService patientCommandService) {
        this.professionalCommandService = professionalCommandService;
        this.patientCommandService = patientCommandService;
    }

    public Long createPatient(String fullName,
                              String email,
                              String phone,
                              LocalDate birthDate,
                             Long userId) {
        var command = new CreatePatientCommand(fullName, email, phone, birthDate);
        var patient = this.patientCommandService.handle(command, userId);
        return patient.map(AuditableAbstractAggregateRoot::getId).orElse(0L);
    }

    public Long createProfessional(String fullName,
                                   String email,
                                   String phone,
                                   LocalDate birthDate,
                                   String professionalType, Long userId) {
        var command = new CreateProfessionalCommand(professionalType, fullName, email, phone, birthDate);
        var professional = this.professionalCommandService.handle(command, userId);
        return professional.map(AuditableAbstractAggregateRoot::getId).orElse(0L);
    }
}
