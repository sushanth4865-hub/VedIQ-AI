INSERT INTO lessons (
    concept_id,
    title,
    introduction,
    explanation,
    real_life_example,
    example_equation,
    exam_points,
    language_code,
    video_url,
    version,
    published
)
SELECT
    c.id,
    'Combination Reactions',
    'A combination reaction is a chemical reaction in which two or more substances combine to form a single product.',
    'In a combination reaction, two or more reactants combine and produce one product. These reactions can occur between elements, compounds, or a combination of both. A common example is calcium oxide reacting with water to produce calcium hydroxide.',
    'When quicklime (calcium oxide) is mixed with water, it reacts vigorously and produces slaked lime (calcium hydroxide). Heat is also released during this reaction.',
    'CaO + H2O → Ca(OH)2',
    'A combination reaction produces a single product from two or more reactants. Remember the general pattern: A + B → AB.',
    'en',
    NULL,
    1,
    TRUE
FROM concepts c
JOIN chapters ch ON c.chapter_id = ch.id
JOIN subjects s ON ch.subject_id = s.id
JOIN grades g ON s.grade_id = g.id
JOIN boards b ON g.board_id = b.id
WHERE b.code = 'AP_SSC'
  AND g.grade_number = 10
  AND s.code = 'SCIENCE'
  AND c.slug = 'combination-reactions';