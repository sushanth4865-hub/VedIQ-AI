CREATE TABLE boards (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    code VARCHAR(50) NOT NULL UNIQUE,
    description TEXT,
    active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE grades (
    id BIGSERIAL PRIMARY KEY,
    board_id BIGINT NOT NULL,
    name VARCHAR(100) NOT NULL,
    grade_number INTEGER NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT fk_grades_board
        FOREIGN KEY (board_id)
        REFERENCES boards (id),

    CONSTRAINT uk_grades_board_grade_number
        UNIQUE (board_id, grade_number)
);

CREATE TABLE subjects (
    id BIGSERIAL PRIMARY KEY,
    grade_id BIGINT NOT NULL,
    name VARCHAR(150) NOT NULL,
    code VARCHAR(50) NOT NULL,
    description TEXT,
    active BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT fk_subjects_grade
        FOREIGN KEY (grade_id)
        REFERENCES grades (id),

    CONSTRAINT uk_subjects_grade_code
        UNIQUE (grade_id, code)
);

CREATE TABLE chapters (
    id BIGSERIAL PRIMARY KEY,
    subject_id BIGINT NOT NULL,
    title VARCHAR(250) NOT NULL,
    chapter_number INTEGER NOT NULL,
    description TEXT,
    active BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT fk_chapters_subject
        FOREIGN KEY (subject_id)
        REFERENCES subjects (id),

    CONSTRAINT uk_chapters_subject_number
        UNIQUE (subject_id, chapter_number)
);

CREATE TABLE concepts (
    id BIGSERIAL PRIMARY KEY,
    chapter_id BIGINT NOT NULL,
    title VARCHAR(250) NOT NULL,
    slug VARCHAR(250) NOT NULL,
    summary TEXT,
    sequence_number INTEGER NOT NULL,
    estimated_learning_minutes INTEGER,
    active BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT fk_concepts_chapter
        FOREIGN KEY (chapter_id)
        REFERENCES chapters (id),

    CONSTRAINT uk_concepts_chapter_slug
        UNIQUE (chapter_id, slug)
);

CREATE TABLE lessons (
    id BIGSERIAL PRIMARY KEY,
    concept_id BIGINT NOT NULL,
    title VARCHAR(250) NOT NULL,
    introduction TEXT,
    explanation TEXT NOT NULL,
    real_life_example TEXT,
    example_equation TEXT,
    exam_points TEXT,
    language_code VARCHAR(10) NOT NULL DEFAULT 'en',
    video_url TEXT,
    version INTEGER NOT NULL DEFAULT 1,
    published BOOLEAN NOT NULL DEFAULT FALSE,

    CONSTRAINT fk_lessons_concept
        FOREIGN KEY (concept_id)
        REFERENCES concepts (id),

    CONSTRAINT uk_lessons_concept_language_version
        UNIQUE (concept_id, language_code, version)
);