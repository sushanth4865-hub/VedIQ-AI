package com.sscai.vediq.academic.repository;

import com.sscai.vediq.academic.domain.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LessonRepository extends JpaRepository<Lesson, Long> {

    Optional<Lesson> findFirstByConceptIdAndLanguageCodeAndPublishedTrueOrderByVersionDesc(
            Long conceptId,
            String languageCode
    );
}