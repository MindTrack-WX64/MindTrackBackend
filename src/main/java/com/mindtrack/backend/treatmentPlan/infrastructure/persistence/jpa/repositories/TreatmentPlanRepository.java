package com.mindtrack.backend.treatmentPlan.infrastructure.persistence.jpa.repositories;

import com.mindtrack.backend.treatmentPlan.domain.model.aggregates.TreatmentPlan;
import com.mindtrack.backend.treatmentPlan.domain.model.valuobjects.TreatmentPlanStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TreatmentPlanRepository extends JpaRepository<TreatmentPlan, Long> {
    Optional<TreatmentPlan> findById(Long id);
    List<TreatmentPlan> findAll();
    //Optional<TreatmentPlan> getAllTreatmentPlanBiologicalFunctions();
    List<TreatmentPlan> getAllByPatientFullName(String patientFullName);
    List<TreatmentPlan> getAllByProfessionalFullName(String professionalFullName);
    /*List<TreatmentPlan> getAllTreatmentPlanDiagnostics();
    List<TreatmentPlan> getAllTreatmentPlanPatientStates();
    List<TreatmentPlan> getAllTreatmentPlanSessions();
    List<TreatmentPlan> getAllTreatmentPlanTasks();
    List<TreatmentPlan> getAllByTasksCompleted(boolean tasksCompleted);**/

    List<TreatmentPlan> getAllByPatientFullNameAndProfessionalFullName(String patientFullName, String professionalFullName);

    @Query("SELECT tp.id AS treatmentPlanId, AVG(bf.hunger) AS averageHunger, AVG(bf.sleep) AS averageSleep, AVG(bf.hydration) AS averageHydration " +
            "FROM TreatmentPlan tp " +
            "JOIN tp.biologicalFunctions bf " +
            "GROUP BY tp.id")
    List<TreatmentPlanStatistics> getTreatmentPlanBiologicalFunctionsStatistics(Long treatmentPlanId);
}
