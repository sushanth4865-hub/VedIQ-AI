# SSC AI Platform — Initial Requirements

## 1. Product Vision

Build an AI-powered personalized learning platform that helps SSC students:

* Understand academic concepts clearly
* Ask questions using an AI tutor
* Practice through quizzes
* Receive explanations for mistakes
* Track their learning progress

The platform should eventually support students, parents, teachers and schools.

## 2. First Vertical Slice

The first feature will provide a complete learning experience for one concept.

### Initial Academic Content

* Board: AP SSC
* Grade: Class 10
* Subject: Science
* Chapter: Chemical Reactions and Equations
* Concept: Combination Reactions

## 3. Student Learning Flow

The student should be able to:

1. Open the Combination Reactions concept
2. Read the lesson
3. View an example chemical equation
4. Ask the AI tutor questions about the concept
5. Take a five-question quiz
6. Submit answers
7. View the score
8. Understand why each answer was correct or incorrect
9. View saved learning progress

## 4. Lesson Requirements

The lesson should contain:

* Concept title
* Short introduction
* Detailed explanation
* Real-life example
* Chemical equation
* Important exam points
* Estimated learning time
* Telugu explanation placeholder
* Video lesson placeholder

## 5. AI Tutor Requirements

The AI tutor should:

* Answer questions related to the selected concept
* Use language suitable for a Class 10 student
* Explain difficult ideas using simple examples
* Provide step-by-step explanations when required
* Avoid answering unrelated questions
* Inform the student politely when a question is outside the approved syllabus
* Store the conversation history

## 6. Quiz Requirements

The initial quiz should contain five questions.

Supported question types:

* Multiple choice
* True or false
* Fill in the blank
* Short answer

For every submitted answer, the platform should return:

* Whether the answer is correct
* The correct answer
* A short explanation
* Marks awarded

## 7. Progress Tracking

The platform should track:

* Whether the lesson was opened
* Whether the lesson was completed
* Number of quiz attempts
* Latest quiz score
* Highest quiz score
* Number of AI tutor questions asked
* Concept mastery status

Initial mastery statuses:

* Not Started
* Learning
* Practicing
* Mastered

## 8. Initial User

The first version will support one development student account.

Authentication and multiple users will be added later.

## 9. Initial Backend APIs

### Get concept

```text
GET /api/v1/concepts/{conceptId}
```

### Send AI tutor message

```text
POST /api/v1/concepts/{conceptId}/tutor/messages
```

### Get tutor conversation

```text
GET /api/v1/concepts/{conceptId}/tutor/messages
```

### Get concept quiz

```text
GET /api/v1/concepts/{conceptId}/quiz
```

### Submit quiz

```text
POST /api/v1/concepts/{conceptId}/quiz/attempts
```

### Get concept progress

```text
GET /api/v1/concepts/{conceptId}/progress
```

## 10. Out of Scope for the First Version

The following will not be included initially:

* Parent dashboard
* Teacher dashboard
* School administration
* Payments
* Live classes
* Mobile application
* Notifications
* Social login
* Multiple boards
* Multiple languages
* Production deployment
* Microservices
* Kafka
* Kubernetes
* Redis
* Advanced analytics

## 11. First Milestone Completion Criteria

The first milestone is complete when:

* The backend application starts successfully
* PostgreSQL is connected
* The concept lesson can be retrieved through an API
* A student can submit quiz answers
* Quiz answers are evaluated
* Progress is stored in the database
* Automated tests pass
* The application can run locally using Docker Compose
