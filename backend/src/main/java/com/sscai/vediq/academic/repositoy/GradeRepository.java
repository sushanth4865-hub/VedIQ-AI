package com.sscai.vediq.academic.repository;

import com.sscai.vediq.academic.domain.Grade;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GradeRepository extends JpaRepository<Grade, Long> {
}