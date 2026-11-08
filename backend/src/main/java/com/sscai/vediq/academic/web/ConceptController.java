package com.sscai.platform.academic.web;

import com.sscai.platform.academic.application.ConceptService;
import com.sscai.platform.academic.application.LessonService;
import com.sscai.platform.academic.application.ConceptCatalogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/concepts")
public class ConceptController {

    private final ConceptService conceptService;
    private final LessonService lessonService;
    private final ConceptCatalogService conceptCatalogService;

    public ConceptController(
            ConceptService conceptService,
            LessonService lessonService,
            ConceptCatalogService conceptCatalogService
    ) {
        this.conceptService = conceptService;
        this.lessonService = lessonService;
        this.conceptCatalogService = conceptCatalogService;
    }

    @GetMapping
    public List<ConceptWithLessonResponse> getAllConcepts(
            @RequestParam(defaultValue = "en") String language
    ) {
        return conceptCatalogService.getAllConcepts(language);
    }

    @GetMapping("/{conceptId}")
    public ConceptResponse getConcept(
            @PathVariable Long conceptId
    ) {
        return conceptService.getConcept(conceptId);
    }

    @GetMapping("/{conceptId}/lesson")
    public LessonResponse getLesson(
            @PathVariable Long conceptId,
            @RequestParam(defaultValue = "en") String language
    ) {
        return lessonService.getLesson(conceptId, language);
    }
}