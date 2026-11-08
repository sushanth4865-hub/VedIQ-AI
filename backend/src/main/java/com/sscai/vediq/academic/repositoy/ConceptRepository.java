package com.sscai.vediq.academic.repository;

import com.sscai.vediq.academic.domain.Concept;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConceptRepository extends JpaRepository<Concept, Long> {
}