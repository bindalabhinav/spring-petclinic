# 🧩 Feature Flag System – Spring PetClinic

## 📌 Overview

This project extends the **Spring PetClinic** application by implementing a **custom Feature Flag system** and integrating it into real application features.

The feature flag system allows application features to be **enabled or disabled dynamically at runtime**, without code changes or redeployment.  
No external feature flag libraries (such as FF4J or Togglz) are used.

---

## 🎯 Objectives

- Build a backend-driven feature flag service from scratch  
- Persist feature flags in a database  
- Expose CRUD APIs for flag management  
- Support advanced feature flag use cases  
- Integrate flags into existing PetClinic features  
- Keep business logic clean using a custom annotation and Spring AOP  

---

## 🚀 How to Run the Application

### Prerequisites
- Java 17
- Maven or Maven Wrapper

### Run locally
```bash
./mvnw spring-boot:run
```

For Windows:
```bash
mvnw.cmd spring-boot:run
```

### Access the application
```
http://localhost:8080
```

---

## 🏗️ Design Decisions & Assumptions

- Feature flags are stored in a relational database (H2 by default)
- Flags persist across application restarts
- Feature flag management APIs are **not authenticated**, as per requirements
- User identity is simulated using a deterministic value (`anonymous-user`)
- Feature evaluation logic is centralized for reuse
- Annotation-based enforcement is used to avoid polluting controller logic

---

## 🎛️ Feature Flag Capabilities

Each feature flag supports the following behaviors:

| Capability | Description |
|---------|------------|
| Global Enable / Disable | Turn feature on or off |
| Whitelist Users | Always allow specific users |
| Blacklist Users | Always block specific users |
| Percentage Rollout | Gradual rollout to a subset of users |

---

## 🧪 Edge Case Handling

| Scenario | Behavior |
|------|--------|
| Feature flag not found | Feature disabled (fail-safe) |
| Enabled = false | Feature blocked |
| User in blacklist | Feature blocked |
| User in whitelist | Feature allowed |
| Rollout percentage = 0 | Feature blocked |
| Rollout percentage = 100 | Feature allowed |
| Application restart | Flags persist |

---

## 🔗 Feature Flagged Application Features

| Application Feature | Flag Name | Description | Code Location |
|-------------------|----------|------------|--------------|
| Add New Pet | ADD_PET | Controls pet creation | PetController |
| Add Visit | ADD_VISIT | Controls visit creation | VisitController |
| Owner Search | OWNER_SEARCH | Controls owner search | OwnerController |

---

## 🔌 Feature Flag Management APIs

### Base Path
```
/api/flags
```

### Create / Update a Flag
```http
POST /api/flags
```

```json
{
  "name": "ADD_PET",
  "enabled": true,
  "rolloutPercentage": 100
}
```

### Get All Flags
```http
GET /api/flags
```

### Get Flag by Name
```http
GET /api/flags/{name}
```

### Delete a Flag
```http
DELETE /api/flags/{name}
```

---

## 🧠 Feature Flag Enforcement

Feature flags are enforced using a **custom annotation**:

```java
@FeatureToggle("ADD_PET")
```

This annotation is processed using **Spring AOP**.  
Before the annotated method executes, the feature flag is evaluated, and execution is either allowed or blocked.

This approach:
- Keeps controller code clean
- Centralizes feature flag logic
- Makes feature control declarative and easy to audit

---

## 🗃️ Database Configuration

- H2 in-memory database is used by default
- Hibernate auto DDL is enabled for schema creation

```properties
spring.jpa.hibernate.ddl-auto=update
```

---


## ✅ Summary

This implementation demonstrates:
- A production-style feature flag system
- Clean Spring Boot integration
- Advanced rollout strategies
- Safe defaults and edge-case handling
- Annotation-based enforcement using AOP

The solution focuses on **clarity, correctness, and real-world applicability**.

---

## 🙌 Author

**Abhinav Bindal**
