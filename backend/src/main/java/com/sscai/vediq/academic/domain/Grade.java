package com.sscai.vediq.academic.domain;

import jakarta.persistence.*;

@Entity
@Table(
        name = "grades",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_grades_board_grade_number",
                        columnNames = {"board_id", "grade_number"}
                )
        }
)
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(name = "grade_number", nullable = false)
    private Integer gradeNumber;

    @Column(nullable = false)
    private boolean active = true;

    protected Grade() {
    }

    public Grade(Board board, String name, Integer gradeNumber) {
        this.board = board;
        this.name = name;
        this.gradeNumber = gradeNumber;
    }

    public Long getId() {
        return id;
    }

    public Board getBoard() {
        return board;
    }

    public String getName() {
        return name;
    }

    public Integer getGradeNumber() {
        return gradeNumber;
    }

    public boolean isActive() {
        return active;
    }
}