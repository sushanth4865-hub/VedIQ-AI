# ADR 0001: Start with a Modular Monolith

* Status: Accepted
* Decision owners: SSC AI engineering team

## Context

The SSC AI platform will eventually include several business areas:

* Academic content
* Student learning
* Quizzes and assessments
* Progress tracking
* AI tutoring
* User management
* Parent and teacher features
* Payments
* Notifications
* Analytics

These areas could eventually become separate services.

However, the product is currently at its earliest stage. The initial goal is to build and validate one complete learning experience for one SSC concept.

The current team is small, the domain is still evolving, and there are no proven scaling requirements yet.

Starting immediately with microservices would introduce additional complexity, including:

* Multiple repositories or deployable applications
* Inter-service communication
* Distributed transactions
* Network failures
* Service discovery
* Centralized logging
* Distributed tracing
* Multiple deployment pipelines
* More infrastructure
* More difficult local development
* More complicated testing
* Increased cloud cost

This complexity would not provide meaningful product value at the current stage.

## Decision

The SSC AI backend will begin as a **modular monolith**.

The application will be deployed as one Spring Boot application, but its internal code will be divided into clearly separated business modules.

Initial modules may include:

```text
com.sscai.platform
├── academic
├── learning
├── assessment
├── progress
├── tutor
├── user
└── shared
```

Each module should contain the code related to its own business responsibility.

For example:

```text
academic
├── domain
├── application
├── infrastructure
└── web
```

The exact package structure may evolve during implementation.

## What Modular Monolith Means

A modular monolith is:

* One deployable backend application
* One running process
* Initially one PostgreSQL database
* Internally separated into business modules
* Designed to prevent uncontrolled dependencies between modules

It is not the same as placing all classes into generic technical folders such as:

```text
controller
service
repository
entity
```

across the entire application.

Instead, classes should first be grouped by business capability.

For example:

```text
academic
├── ConceptController
├── ConceptService
├── ConceptRepository
├── Concept
└── Lesson
```

This keeps related business code together.

## Module Communication

Modules should communicate through defined interfaces and application services.

A module should not freely access the internal implementation details of another module.

For example, the assessment module may request concept information through an interface exposed by the academic module.

It should avoid directly modifying academic tables or relying on internal academic classes.

## Database Decision

The initial application will use one PostgreSQL database.

Each module will own its relevant tables logically, even though all tables are stored in the same database.

Example ownership:

```text
Academic module
- boards
- grades
- subjects
- chapters
- concepts
- lessons

Assessment module
- quizzes
- questions
- question_options
- quiz_attempts
- quiz_answers

Progress module
- concept_progress

Tutor module
- tutor_conversations
- tutor_messages
```

Database schemas may be separated later if useful, but separate databases are not required initially.

## Reasons for This Decision

### Faster product development

One application is easier to build, run, test and debug while the product requirements are still changing.

### Easier local development

Developers can start the backend, frontend and database without managing several distributed services.

### Simpler transactions

Operations such as submitting a quiz and updating student progress can initially run within one database transaction.

### Lower infrastructure cost

The first version does not require multiple application deployments, message brokers, service discovery or a Kubernetes cluster.

### Better domain discovery

The correct service boundaries are not yet proven.

Building the domain as modules allows us to discover those boundaries before splitting the system.

### Easier refactoring

Moving code between modules inside one codebase is easier than moving behavior between independently deployed services.

## Consequences

### Positive consequences

* Faster initial development
* Simpler debugging
* Easier automated testing
* Simpler deployment
* Lower operational overhead
* Lower cloud cost
* Easier database transactions
* Clearer understanding of the domain before service extraction

### Negative consequences

* A poorly enforced modular structure could become a tightly coupled monolith
* All modules initially scale together
* One deployment can affect the entire backend
* One database can become a coupling point
* Developers must actively prevent cross-module shortcuts

## Rules to Prevent a Big Ball of Mud

The following rules will be applied:

1. Organize code by business module.
2. Keep module internals private where possible.
3. Avoid direct repository access across modules.
4. Use interfaces for important module interactions.
5. Avoid circular module dependencies.
6. Keep shared code minimal.
7. Do not place business logic in controllers.
8. Do not create a generic shared service package.
9. Add architecture tests later to enforce module boundaries.
10. Document significant cross-module decisions.

## When Microservices May Be Considered

A module may be considered for extraction when one or more of these conditions are present:

* It requires independent scaling.
* It has a clearly stable business boundary.
* It requires an independent deployment cycle.
* It is owned by a separate engineering team.
* It has significantly different availability requirements.
* It requires a different technology or data-storage model.
* Failures in that module must be isolated from the rest of the platform.
* The modular monolith is causing measurable delivery or operational problems.

Microservices will not be introduced only for résumé value or architectural appearance.

## Possible Future Service Candidates

Potential future services may include:

* AI tutor service
* Assessment service
* Notification service
* Payment service
* Analytics service
* Content service

These are possibilities, not current commitments.

## Alternatives Considered

### Microservices from the beginning

Rejected because the product boundaries, traffic patterns and team structure are not yet known.

The operational complexity would be greater than the current business benefit.

### Traditional layered monolith

Rejected because grouping the entire application into global controller, service and repository packages can make business boundaries unclear as the application grows.

### Serverless-first architecture

Not selected for the initial core backend because the main application has connected domain workflows, relational data and transactional requirements.

Serverless components may still be used later for specific asynchronous or event-driven tasks.

## Result

The SSC AI platform will start as a modular monolith built with Spring Boot and PostgreSQL.

The architecture will preserve clear business boundaries so that selected modules can be extracted into independent services later when there is a demonstrated reason.
