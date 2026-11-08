package com.sscai.vediq.academic.application;

import com.sscai.vediq.academic.domain.Concept;
import com.sscai.vediq.academic.repository.ConceptRepository;
import com.sscai.vediq.academic.web.ConceptResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConceptService {

    private final ConceptRepository conceptRepository;

    public ConceptService(ConceptRepository conceptRepository) {
        this.conceptRepository = conceptRepository;
    }

    @Transactional(readOnly = true)
    public ConceptResponse getConcept(Long conceptId) {

        Concept concept = conceptRepository.findById(conceptId)
                .orElseThrow(() ->
                        new IllegalArgumentException(
                                "Concept not found: " + conceptId
                        )
                );

        return new ConceptResponse(
                concept.getId(),
                concept.getTitle(),
                concept.getSlug(),
                concept.getSummary(),
                concept.getSequenceNumber(),
                concept.getEstimatedLearningMinutes(),
                concept.getChapter().getTitle(),
                concept.getChapter().getSubject().getName(),
                concept.getChapter().getSubject().getGrade().getGradeNumber(),
                concept.getChapter().getSubject().getGrade().getBoard().getName()
        );
    }
}