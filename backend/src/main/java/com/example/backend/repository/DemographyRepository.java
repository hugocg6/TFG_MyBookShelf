package com.example.backend.repository;

import com.example.backend.model.Demography;
import com.example.backend.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemographyRepository extends JpaRepository<Demography, Long> {
}
