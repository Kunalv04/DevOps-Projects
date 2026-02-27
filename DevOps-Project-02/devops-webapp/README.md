# DevOps Basics WebApp

A Java-based web application built with Spring Boot and Maven that demonstrates the fundamentals of DevOps. This educational webapp covers core DevOps concepts, tools, lifecycle phases, and best practices.

## Features

- **Home Page** - Overview of DevOps with key concepts and benefits
- **Concepts Page** - Detailed explanation of CI/CD, IaC, Microservices, Monitoring, and Collaboration
- **Tools Page** - Essential DevOps tools across categories like Version Control, CI/CD, Containers, and more
- **Lifecycle Page** - The 8 phases of the DevOps infinity loop
- **Practices Page** - Industry best practices and the CALMS framework
- **About Page** - Information about the application stack and architecture

## Technology Stack

- **Java 17** - Programming language
- **Spring Boot 3.2.0** - Application framework
- **Maven** - Build tool and dependency management
- **Thymeleaf** - Server-side templating engine
- **Spring Boot Actuator** - Monitoring and management endpoints
- **Spring Boot DevTools** - Development hot reload

## Project Structure

```
devops-webapp/
├── pom.xml                          # Maven configuration
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/devops/basics/
│   │   │       ├── DevOpsBasicsApplication.java    # Main Spring Boot class
│   │   │       ├── controller/
│   │   │       │   └── DevOpsController.java       # Web request handlers
│   │   │       └── model/
│   │   │           └── DevOpsConcept.java          # Data model
│   │   └── resources/
│   │       ├── application.properties              # App configuration
│   │       ├── templates/                          # Thymeleaf HTML templates
│   │       │   ├── index.html
│   │       │   ├── concepts.html
│   │       │   ├── tools.html
│   │       │   ├── lifecycle.html
│   │       │   ├── practices.html
│   │       │   └── about.html
│   │       └── static/
│   │           └── css/
│   │               └── style.css                   # Stylesheet
│   └── test/                                       # Unit tests
└── target/                                         # Build output
```

## Dependencies (pom.xml)

```xml
<!-- Spring Boot Starter Web - For building web applications -->
spring-boot-starter-web

<!-- Spring Boot Starter Thymeleaf - For HTML templating -->
spring-boot-starter-thymeleaf

<!-- Spring Boot Starter Actuator - For monitoring and metrics -->
spring-boot-starter-actuator

<!-- Spring Boot DevTools - For development hot reload -->
spring-boot-devtools

<!-- Spring Boot Starter Test - For testing -->
spring-boot-starter-test
```

## How to Run

### Prerequisites

- Java 17 or higher
- Maven 3.6+ installed

### Build and Run

1. **Clone or navigate to the project directory:**
   ```bash
   cd devops-webapp
   ```

2. **Build the application:**
   ```bash
   mvn clean package
   ```

3. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the application:**
   Open your browser and navigate to: `http://localhost:8080`

### Alternative: Run the JAR

After building:
```bash
java -jar target/devops-basics-webapp-1.0.0.jar
```

## Application Endpoints

| Endpoint | Description |
|----------|-------------|
| `/` | Home page with DevOps overview |
| `/concepts` | Core DevOps concepts |
| `/tools` | DevOps tools by category |
| `/lifecycle` | DevOps lifecycle phases |
| `/practices` | Best practices and CALMS framework |
| `/about` | Application information |
| `/actuator/health` | Health check endpoint |
| `/actuator/info` | Application info endpoint |

## Screenshots

The application features a modern dark theme with:
- Responsive navigation bar
- Gradient accents and animations
- Card-based layouts for content
- Mobile-responsive design

## DevOps Topics Covered

### Concepts
- Continuous Integration (CI)
- Continuous Delivery (CD)
- Infrastructure as Code (IaC)
- Microservices
- Monitoring and Logging
- Collaboration and Communication

### Tools
- Version Control (Git, GitHub, GitLab)
- CI/CD (Jenkins, GitLab CI, GitHub Actions)
- Containerization (Docker, Kubernetes)
- Infrastructure as Code (Terraform, Ansible)
- Monitoring (Prometheus, Grafana, ELK)
- Cloud Platforms (AWS, Azure, GCP)

### Lifecycle Phases
1. Plan
2. Code
3. Build
4. Test
5. Release
6. Deploy
7. Operate
8. Monitor

### Best Practices
- Automate Everything
- Shift Left Testing
- Implement GitOps
- Build Security In (DevSecOps)
- Use Immutable Infrastructure
- Monitor Everything
- Foster Collaboration

## Development

### Hot Reload
Spring Boot DevTools is enabled for automatic restart during development.

### Running Tests
```bash
mvn test
```

## License

This project is for educational purposes.

## Author

Built with Java, Spring Boot & Maven
