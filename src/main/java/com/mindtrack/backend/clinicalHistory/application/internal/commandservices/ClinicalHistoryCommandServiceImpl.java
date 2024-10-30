package com.mindtrack.backend.clinicalHistory.application.internal.commandservices;

import com.mindtrack.backend.clinicalHistory.domain.model.aggregates.ClinicalHistory;
import com.mindtrack.backend.clinicalHistory.domain.model.commands.AddSymptomCommand;
import com.mindtrack.backend.clinicalHistory.domain.model.commands.CreateClinicalHistoryCommand;
import com.mindtrack.backend.clinicalHistory.domain.services.ClinicalHistoryCommandService;
import com.mindtrack.backend.clinicalHistory.infrastructure.persistence.jpa.repositories.ClinicalHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClinicalHistoryCommandServiceImpl implements ClinicalHistoryCommandService {
    private final ClinicalHistoryRepository clinicalHistoryRepository;

    public ClinicalHistoryCommandServiceImpl(ClinicalHistoryRepository clinicalHistoryRepository) {
        this.clinicalHistoryRepository = clinicalHistoryRepository;
    }

    @Override
    public Optional<ClinicalHistory> handle(CreateClinicalHistoryCommand command) {
        /*Patient patient = this.patientRepository.findById(command.patientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        if (patient.isClinicalHistoryStatus()) {
            throw new RuntimeException("Patient already has a clinical history");
        }*/

        ClinicalHistory clinicalHistory = new ClinicalHistory(command);

        // patient.updateClinicalHistoryStatus();

        var clinicalHistorySaved = this.clinicalHistoryRepository.save(clinicalHistory);
        return Optional.of(clinicalHistorySaved);
    }

    @Override
    public Optional<ClinicalHistory> handle(AddSymptomCommand command) {
        ClinicalHistory clinicalHistory = this.clinicalHistoryRepository.findById(command.clinicalHistoryId())
                .orElseThrow(() -> new RuntimeException("Clinical history not found"));

        clinicalHistory.addSymptom(command);

        var clinicalHistoryUpdated = this.clinicalHistoryRepository.save(clinicalHistory);
        return Optional.of(clinicalHistoryUpdated);
    }
}
