package com.sscai.vediq.academic.web;

public record LessonResponse(
        Long id,
        Long conceptId,
        String conceptTitle,
        String title,
        String introduction,
        String explanation,
        String realLifeExample,
        String exampleEquation,
        String examPoints,
        String languageCode,
        String videoUrl,
        Integer version
) {
}