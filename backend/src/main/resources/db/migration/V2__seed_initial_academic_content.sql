INSERT INTO boards (name, code, description)
VALUES (
    'Andhra Pradesh SSC',
    'AP_SSC',
    'Andhra Pradesh Board of Secondary Education'
);

INSERT INTO grades (board_id, name, grade_number)
SELECT id, 'Class 10', 10
FROM boards
WHERE code = 'AP_SSC';

INSERT INTO subjects (grade_id, name, code, description)
SELECT
    g.id,
    'Science',
    'SCIENCE',
    'Class 10 Science'
FROM grades g
JOIN boards b ON g.board_id = b.id
WHERE b.code = 'AP_SSC'
  AND g.grade_number = 10;

INSERT INTO chapters (
    subject_id,
    title,
    chapter_number,
    description
)
SELECT
    s.id,
    'Chemical Reactions and Equations',
    1,
    'Introduction to chemical reactions, equations and different types of chemical reactions.'
FROM subjects s
JOIN grades g ON s.grade_id = g.id
JOIN boards b ON g.board_id = b.id
WHERE b.code = 'AP_SSC'
  AND g.grade_number = 10
  AND s.code = 'SCIENCE';

INSERT INTO concepts (
    chapter_id,
    title,
    slug,
    summary,
    sequence_number,
    estimated_learning_minutes
)
SELECT
    c.id,
    'Combination Reactions',
    'combination-reactions',
    'Understanding reactions in which two or more substances combine to form a single product.',
    1,
    15
FROM chapters c
JOIN subjects s ON c.subject_id = s.id
JOIN grades g ON s.grade_id = g.id
JOIN boards b ON g.board_id = b.id
WHERE b.code = 'AP_SSC'
  AND g.grade_number = 10
  AND s.code = 'SCIENCE'
  AND c.chapter_number = 1;