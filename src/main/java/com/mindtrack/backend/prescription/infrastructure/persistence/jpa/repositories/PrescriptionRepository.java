package com.mindtrack.backend.prescription.infrastructure.persistence.jpa.repositories;

import com.mindtrack.backend.prescription.domain.model.aggregates.Prescription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PrescriptionRepository extends JpaRepository<Prescription, Long> {
    Optional<Prescription> findById(Long id);

    List<Prescription> findAll();

    @Query("SELECT p FROM Prescription p JOIN p.pills pill WHERE pill.name = :pillName")
    List<Prescription> findPrescriptionsByPillName(@Param("pillName") String pillName);
}
