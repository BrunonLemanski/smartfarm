package com.lemanski.SmartFarm.repository;

import com.lemanski.SmartFarm.model.database.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
}
