package com.sscai.vediq.academic.application;

import com.sscai.vediq.academic.domain.Lesson;
import com.sscai.vediq.academic.repository.LessonRepository;
import com.sscai.vediq.academic.web.LessonResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LessonService {

    private final LessonRepository lessonRepository;

    public LessonService(LessonRepository lessonRepository) {
        this.lessonRepository = lessonRepository;
    }

    @Transactional(readOnly = true)
    public LessonResponse getLesson(
            Long conceptId,
            String languageCode
    ) {

        Lesson lesson = lessonRepository
                .findFirstByConceptIdAndLanguageCodeAndPublishedTrueOrderByVersionDesc(
                        conceptId,
                        languageCode
                )
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Published lesson not found for concept: " + conceptId
                        )
                );

        return new LessonResponse(
                lesson.getId(),
                lesson.getConcept().getId(),
                lesson.getConcept().getTitle(),
                lesson.getTitle(),
                lesson.getIntroduction(),
                lesson.getExplanation(),
                lesson.getRealLifeExample(),
                lesson.getExampleEquation(),
                lesson.getExamPoints(),
                lesson.getLanguageCode(),
                lesson.getVideoUrl(),
                lesson.getVersion()
        );
    }
}