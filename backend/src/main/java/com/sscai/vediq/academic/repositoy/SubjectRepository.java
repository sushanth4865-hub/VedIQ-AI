package com.sscai.vediq.academic.repository;

import com.sscai.vediq.academic.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
}