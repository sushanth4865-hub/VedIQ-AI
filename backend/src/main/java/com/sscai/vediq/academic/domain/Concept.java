package com.sscai.vediq.academic.domain;

import jakarta.persistence.*;

@Entity
@Table(
        name = "concepts",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_concepts_chapter_slug",
                        columnNames = {"chapter_id", "slug"}
                )
        }
)
public class Concept {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "chapter_id", nullable = false)
    private Chapter chapter;

    @Column(nullable = false, length = 250)
    private String title;

    @Column(nullable = false, length = 250)
    private String slug;

    @Column(columnDefinition = "TEXT")
    private String summary;

    @Column(name = "sequence_number", nullable = false)
    private Integer sequenceNumber;

    @Column(name = "estimated_learning_minutes")
    private Integer estimatedLearningMinutes;

    @Column(nullable = false)
    private boolean active = true;

    protected Concept() {
    }

    public Concept(
            Chapter chapter,
            String title,
            String slug,
            String summary,
            Integer sequenceNumber,
            Integer estimatedLearningMinutes
    ) {
        this.chapter = chapter;
        this.title = title;
        this.slug = slug;
        this.summary = summary;
        this.sequenceNumber = sequenceNumber;
        this.estimatedLearningMinutes = estimatedLearningMinutes;
    }

    public Long getId() {
        return id;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public String getTitle() {
        return title;
    }

    public String getSlug() {
        return slug;
    }

    public String getSummary() {
        return summary;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public Integer getEstimatedLearningMinutes() {
        return estimatedLearningMinutes;
    }

    public boolean isActive() {
        return active;
    }
}