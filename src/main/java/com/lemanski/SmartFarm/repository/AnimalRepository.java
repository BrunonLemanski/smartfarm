package com.lemanski.SmartFarm.repository;

import com.lemanski.SmartFarm.model.database.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
