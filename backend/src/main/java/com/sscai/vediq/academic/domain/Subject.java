package com.sscai.vediq.academic.domain;

import jakarta.persistence.*;

@Entity
@Table(
        name = "subjects",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_subjects_grade_code",
                        columnNames = {"grade_id", "code"}
                )
        }
)
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "grade_id", nullable = false)
    private Grade grade;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(nullable = false, length = 50)
    private String code;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private boolean active = true;

    protected Subject() {
    }

    public Subject(Grade grade, String name, String code, String description) {
        this.grade = grade;
        this.name = name;
        this.code = code;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public Grade getGrade() {
        return grade;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return active;
    }
}