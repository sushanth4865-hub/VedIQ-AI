package com.sscai.vediq.academic.domain;

import jakarta.persistence.*;

@Entity
@Table(
        name = "chapters",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_chapters_subject_number",
                        columnNames = {"subject_id", "chapter_number"}
                )
        }
)
public class Chapter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "subject_id", nullable = false)
    private Subject subject;

    @Column(nullable = false, length = 250)
    private String title;

    @Column(name = "chapter_number", nullable = false)
    private Integer chapterNumber;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private boolean active = true;

    protected Chapter() {
    }

    public Chapter(
            Subject subject,
            String title,
            Integer chapterNumber,
            String description
    ) {
        this.subject = subject;
        this.title = title;
        this.chapterNumber = chapterNumber;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public Subject getSubject() {
        return subject;
    }

    public String getTitle() {
        return title;
    }

    public Integer getChapterNumber() {
        return chapterNumber;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return active;
    }
}