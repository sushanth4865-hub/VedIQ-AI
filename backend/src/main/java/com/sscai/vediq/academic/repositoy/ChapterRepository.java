package com.sscai.vediq.academic.repository;

import com.sscai.vediq.academic.domain.Chapter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChapterRepository extends JpaRepository<Chapter, Long> {
}