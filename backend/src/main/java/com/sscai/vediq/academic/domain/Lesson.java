package com.sscai.vediq.academic.domain;

import jakarta.persistence.*;

@Entity
@Table(
        name = "lessons",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_lessons_concept_language_version",
                        columnNames = {"concept_id", "language_code", "version"}
                )
        }
)
public class Lesson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "concept_id", nullable = false)
    private Concept concept;

    @Column(nullable = false, length = 250)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String introduction;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String explanation;

    @Column(name = "real_life_example", columnDefinition = "TEXT")
    private String realLifeExample;

    @Column(name = "example_equation", columnDefinition = "TEXT")
    private String exampleEquation;

    @Column(name = "exam_points", columnDefinition = "TEXT")
    private String examPoints;

    @Column(name = "language_code", nullable = false, length = 10)
    private String languageCode = "en";

    @Column(name = "video_url", columnDefinition = "TEXT")
    private String videoUrl;

    @Column(nullable = false)
    private Integer version = 1;

    @Column(nullable = false)
    private boolean published = false;

    protected Lesson() {
    }

    public Lesson(
            Concept concept,
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
        this.concept = concept;
        this.title = title;
        this.introduction = introduction;
        this.explanation = explanation;
        this.realLifeExample = realLifeExample;
        this.exampleEquation = exampleEquation;
        this.examPoints = examPoints;
        this.languageCode = languageCode;
        this.videoUrl = videoUrl;
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public Concept getConcept() {
        return concept;
    }

    public String getTitle() {
        return title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public String getExplanation() {
        return explanation;
    }

    public String getRealLifeExample() {
        return realLifeExample;
    }

    public String getExampleEquation() {
        return exampleEquation;
    }

    public String getExamPoints() {
        return examPoints;
    }

    public String getLanguageCode() {
        return languageCode;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public Integer getVersion() {
        return version;
    }

    public boolean isPublished() {
        return published;
    }
}