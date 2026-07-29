# SSC AI Platform — Initial Domain Model

## 1. Purpose

This document defines the main business entities required for the initial requirements:

* Open a concept lesson
* Ask the AI tutor questions
* Take a quiz
* Save student progress

This is the initial model and will evolve as the product grows.

---

## 2. Academic Structure

The academic content follows this hierarchy:

```text
Board
└── Grade
    └── Subject
        └── Chapter
            └── Concept
                ├── Lesson
                └── Quiz
```

Example:

```text
AP SSC
└── Class 10
    └── Science
        └── Chemical Reactions and Equations
            └── Combination Reactions
```

---

## 3. Core Entities

### Board

Represents an education board.

Example:

```text
AP SSC
```

Initial fields:

* `id`
* `name`
* `code`
* `description`
* `active`

---

### Grade

Represents a student grade or class.

Example:

```text
Class 10
```

Initial fields:

* `id`
* `boardId`
* `name`
* `gradeNumber`
* `active`

Relationship:

* One board can contain many grades.
* Each grade belongs to one board.

---

### Subject

Represents an academic subject.

Example:

```text
Science
```

Initial fields:

* `id`
* `gradeId`
* `name`
* `code`
* `description`
* `active`

Relationship:

* One grade can contain many subjects.
* Each subject belongs to one grade.

---

### Chapter

Represents a chapter inside a subject.

Example:

```text
Chemical Reactions and Equations
```

Initial fields:

* `id`
* `subjectId`
* `title`
* `chapterNumber`
* `description`
* `active`

Relationship:

* One subject can contain many chapters.
* Each chapter belongs to one subject.

---

### Concept

Represents one learnable academic topic inside a chapter.

Example:

```text
Combination Reactions
```

Initial fields:

* `id`
* `chapterId`
* `title`
* `slug`
* `summary`
* `sequenceNumber`
* `estimatedLearningMinutes`
* `active`

Relationship:

* One chapter can contain many concepts.
* Each concept belongs to one chapter.

A concept is the central unit for:

* Lessons
* AI tutor conversations
* Quizzes
* Student progress

---

### Lesson

Represents the instructional content for a concept.

Initial fields:

* `id`
* `conceptId`
* `title`
* `introduction`
* `explanation`
* `realLifeExample`
* `exampleEquation`
* `examPoints`
* `languageCode`
* `videoUrl`
* `version`
* `published`

Relationship:

* One concept can eventually contain multiple lessons.
* Each lesson belongs to one concept.

Multiple lessons may later support:

* Different languages
* Different difficulty levels
* Revision lessons
* Exam-focused lessons

For the first version, each concept will have one English lesson.

---

## 4. Student Entities

### User

Represents a person who can access the system.

Initial fields:

* `id`
* `email`
* `displayName`
* `userType`
* `active`
* `createdAt`
* `updatedAt`

Initial user type:

```text
STUDENT
```

Authentication will be added later.

---

### StudentProfile

Represents student-specific information.

Initial fields:

* `id`
* `userId`
* `boardId`
* `gradeId`
* `schoolName`
* `preferredLanguage`
* `createdAt`
* `updatedAt`

Relationship:

* One user has one student profile.
* Each student profile belongs to one board and grade.

---

## 5. Quiz Entities

### Quiz

Represents an assessment for a concept.

Initial fields:

* `id`
* `conceptId`
* `title`
* `description`
* `passingScorePercentage`
* `active`

Relationship:

* One concept can have one or more quizzes.
* Each quiz belongs to one concept.

For the first version, each concept will have one quiz.

---

### Question

Represents one quiz question.

Initial fields:

* `id`
* `quizId`
* `questionText`
* `questionType`
* `correctAnswer`
* `explanation`
* `marks`
* `sequenceNumber`
* `active`

Supported question types:

```text
MULTIPLE_CHOICE
TRUE_FALSE
FILL_IN_THE_BLANK
SHORT_ANSWER
```

Relationship:

* One quiz contains many questions.
* Each question belongs to one quiz.

---

### QuestionOption

Represents an option for a multiple-choice question.

Initial fields:

* `id`
* `questionId`
* `optionText`
* `optionKey`
* `sequenceNumber`

Example:

```text
A — Two or more substances form one product
B — One substance breaks into two products
C — Ions exchange between two compounds
D — Oxygen is always released
```

Relationship:

* One question can contain many options.
* Each option belongs to one question.

Only multiple-choice questions require options.

---

### QuizAttempt

Represents one student submission of a quiz.

Initial fields:

* `id`
* `quizId`
* `studentId`
* `score`
* `maximumScore`
* `scorePercentage`
* `passed`
* `attemptNumber`
* `startedAt`
* `submittedAt`

Relationship:

* One student can make many quiz attempts.
* One quiz can have many quiz attempts.

---

### QuizAnswer

Represents a student's answer to one question during an attempt.

Initial fields:

* `id`
* `quizAttemptId`
* `questionId`
* `submittedAnswer`
* `correct`
* `marksAwarded`
* `feedback`

Relationship:

* One quiz attempt contains many quiz answers.
* Each quiz answer belongs to one question.

---

## 6. Progress Entity

### ConceptProgress

Represents a student's progress for one concept.

Initial fields:

* `id`
* `studentId`
* `conceptId`
* `status`
* `lessonOpened`
* `lessonCompleted`
* `quizAttemptCount`
* `latestQuizScore`
* `highestQuizScore`
* `tutorQuestionCount`
* `firstOpenedAt`
* `lastActivityAt`
* `completedAt`

Supported statuses:

```text
NOT_STARTED
LEARNING
PRACTICING
MASTERED
```

Relationship:

* One student has one progress record per concept.
* One concept can have progress records for many students.

Business rule:

```text
studentId + conceptId must be unique
```

---

## 7. AI Tutor Entities

### TutorConversation

Represents an AI tutoring conversation between a student and a concept tutor.

Initial fields:

* `id`
* `studentId`
* `conceptId`
* `title`
* `createdAt`
* `updatedAt`

Relationship:

* One student can have many tutor conversations.
* Each tutor conversation belongs to one concept.

---

### TutorMessage

Represents one message in a tutor conversation.

Initial fields:

* `id`
* `conversationId`
* `role`
* `content`
* `createdAt`

Supported roles:

```text
STUDENT
ASSISTANT
SYSTEM
```

Relationship:

* One tutor conversation contains many messages.
* Each message belongs to one conversation.

---

## 8. Initial Entity Relationships

```text
Board
  └── Grade
       └── Subject
            └── Chapter
                 └── Concept
                      ├── Lesson
                      ├── Quiz
                      │    └── Question
                      │         └── QuestionOption
                      ├── ConceptProgress
                      └── TutorConversation
                           └── TutorMessage

User
  └── StudentProfile
       ├── ConceptProgress
       ├── QuizAttempt
       │    └── QuizAnswer
       └── TutorConversation
```

---

## 9. Important Design Decisions

### Concept and Lesson are separate

A concept represents the academic topic.

A lesson represents one instructional version of that concept.

This allows the same concept to support:

* English lessons
* Telugu lessons
* Beginner explanations
* Advanced explanations
* Revision material
* Exam preparation material

---

### QuizAttempt and QuizAnswer are separate

A quiz attempt stores the overall result.

Quiz answers store the student's response and feedback for every question.

This supports:

* Detailed result review
* Weak-area analysis
* Question-level analytics
* Future AI-generated recommendations

---

### Progress is stored separately

Progress should not be calculated every time by reading every lesson, quiz and tutor record.

A dedicated `ConceptProgress` entity gives the system a fast summary of the student's current learning state.

Detailed attempt history remains available in the related tables.

---

## 10. Initial Scope Reduction

Although all entities are documented now, the first backend implementation will begin with only:

* Board
* Grade
* Subject
* Chapter
* Concept
* Lesson

After lesson retrieval works, we will add:

* Quiz
* Question
* QuestionOption
* QuizAttempt
* QuizAnswer
* ConceptProgress

The AI tutor entities will be added after the core learning and quiz flow works.

This incremental approach prevents us from building the entire system before validating the first feature.
