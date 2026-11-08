package com.sscai.vediq.academic.web;

public record ConceptResponse(
        Long id,
        String title,
        String slug,
        String summary,
        Integer sequenceNumber,
        Integer estimatedLearningMinutes,
        String chapter,
        String subject,
        Integer grade,
        String board
) {
}