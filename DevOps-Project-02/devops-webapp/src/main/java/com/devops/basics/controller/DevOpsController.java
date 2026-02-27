package com.devops.basics.controller;

import com.devops.basics.model.DevOpsConcept;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

/**
 * Controller class for handling DevOps Basics web requests.
 * 
 * This controller manages the routes for displaying DevOps concepts,
 * tools, practices, and lifecycle information.
 * 
 * @author DevOps Team
 * @version 1.0.0
 */
@Controller
public class DevOpsController {

    /**
     * Home page - displays overview of DevOps.
     * 
     * @param model the Spring MVC model
     * @return the home page template name
     */
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("title", "DevOps Basics - Home");
        model.addAttribute("message", "Welcome to DevOps Basics!");
        return "index";
    }

    /**
     * Concepts page - displays key DevOps concepts.
     * 
     * @param model the Spring MVC model
     * @return the concepts page template name
     */
    @GetMapping("/concepts")
    public String concepts(Model model) {
        List<DevOpsConcept> concepts = getDevOpsConcepts();
        model.addAttribute("title", "DevOps Concepts");
        model.addAttribute("concepts", concepts);
        return "concepts";
    }

    /**
     * Tools page - displays DevOps tools categories.
     * 
     * @param model the Spring MVC model
     * @return the tools page template name
     */
    @GetMapping("/tools")
    public String tools(Model model) {
        model.addAttribute("title", "DevOps Tools");
        model.addAttribute("tools", getDevOpsTools());
        return "tools";
    }

    /**
     * Lifecycle page - displays DevOps lifecycle phases.
     * 
     * @param model the Spring MVC model
     * @return the lifecycle page template name
     */
    @GetMapping("/lifecycle")
    public String lifecycle(Model model) {
        model.addAttribute("title", "DevOps Lifecycle");
        model.addAttribute("phases", getLifecyclePhases());
        return "lifecycle";
    }

    /**
     * Practices page - displays DevOps best practices.
     * 
     * @param model the Spring MVC model
     * @return the practices page template name
     */
    @GetMapping("/practices")
    public String practices(Model model) {
        model.addAttribute("title", "DevOps Best Practices");
        model.addAttribute("practices", getBestPractices());
        return "practices";
    }

    /**
     * About page - displays information about the application.
     * 
     * @param model the Spring MVC model
     * @return the about page template name
     */
    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About DevOps Basics");
        model.addAttribute("version", "1.0.0");
        model.addAttribute("javaVersion", System.getProperty("java.version"));
        return "about";
    }

    /**
     * Creates and returns a list of DevOps concepts.
     * 
     * @return list of DevOpsConcept objects
     */
    private List<DevOpsConcept> getDevOpsConcepts() {
        List<DevOpsConcept> concepts = new ArrayList<>();
        
        concepts.add(new DevOpsConcept(
            "Continuous Integration (CI)",
            "ci",
            "Continuous Integration is a development practice where developers integrate code into a shared repository frequently. Each integration is verified by an automated build and automated tests.",
            new String[]{"Faster bug detection", "Reduced integration problems", "Faster development cycles"}
        ));
        
        concepts.add(new DevOpsConcept(
            "Continuous Delivery (CD)",
            "cd",
            "Continuous Delivery is the ability to get changes of all types into production safely and quickly in a sustainable way.",
            new String[]{"Automated release process", "Faster time to market", "Lower risk releases"}
        ));
        
        concepts.add(new DevOpsConcept(
            "Infrastructure as Code (IaC)",
            "iac",
            "Infrastructure as Code is the practice of managing and provisioning infrastructure through code instead of manual processes.",
            new String[]{"Version control for infrastructure", "Consistent environments", "Faster provisioning"}
        ));
        
        concepts.add(new DevOpsConcept(
            "Microservices",
            "microservices",
            "Microservices architecture structures an application as a collection of loosely coupled services, which implement business capabilities.",
            new String[]{"Scalability", "Technology diversity", "Resilience"}
        ));
        
        concepts.add(new DevOpsConcept(
            "Monitoring and Logging",
            "monitoring",
            "Continuous monitoring and logging provide real-time insights into application performance and help quickly identify and resolve issues.",
            new String[]{"Proactive issue detection", "Performance optimization", "Better user experience"}
        ));
        
        concepts.add(new DevOpsConcept(
            "Collaboration and Communication",
            "collaboration",
            "DevOps emphasizes collaboration between development and operations teams, breaking down silos and improving communication.",
            new String[]{"Faster problem resolution", "Shared responsibility", "Improved efficiency"}
        ));
        
        return concepts;
    }

    /**
     * Creates and returns a list of DevOps tool categories.
     * 
     * @return list of DevOpsConcept objects representing tool categories
     */
    private List<DevOpsConcept> getDevOpsTools() {
        List<DevOpsConcept> tools = new ArrayList<>();
        
        tools.add(new DevOpsConcept(
            "Version Control",
            "vcs",
            "Tools for tracking and managing changes to code. Git is the most popular distributed version control system.",
            new String[]{"Git", "GitHub", "GitLab", "Bitbucket", "SVN"}
        ));
        
        tools.add(new DevOpsConcept(
            "CI/CD Tools",
            "cicd",
            "Tools for automating the build, test, and deployment processes.",
            new String[]{"Jenkins", "GitLab CI", "GitHub Actions", "CircleCI", "Travis CI", "Azure DevOps"}
        ));
        
        tools.add(new DevOpsConcept(
            "Containerization",
            "containers",
            "Tools for packaging applications with their dependencies into standardized units.",
            new String[]{"Docker", "containerd", "Podman", "LXC"}
        ));
        
        tools.add(new DevOpsConcept(
            "Orchestration",
            "orchestration",
            "Tools for automating the deployment, scaling, and management of containerized applications.",
            new String[]{"Kubernetes", "Docker Swarm", "OpenShift", "Nomad"}
        ));
        
        tools.add(new DevOpsConcept(
            "Infrastructure as Code",
            "iac-tools",
            "Tools for managing infrastructure through code and automation.",
            new String[]{"Terraform", "Ansible", "Puppet", "Chef", "CloudFormation", "Pulumi"}
        ));
        
        tools.add(new DevOpsConcept(
            "Monitoring & Observability",
            "observability",
            "Tools for tracking application performance, logs, and metrics.",
            new String[]{"Prometheus", "Grafana", "ELK Stack", "Datadog", "New Relic", "Jaeger"}
        ));
        
        tools.add(new DevOpsConcept(
            "Cloud Platforms",
            "cloud",
            "Cloud service providers for hosting and managing applications.",
            new String[]{"AWS", "Azure", "Google Cloud Platform", "IBM Cloud", "Oracle Cloud"}
        ));
        
        return tools;
    }

    /**
     * Creates and returns a list of DevOps lifecycle phases.
     * 
     * @return list of DevOpsConcept objects representing lifecycle phases
     */
    private List<DevOpsConcept> getLifecyclePhases() {
        List<DevOpsConcept> phases = new ArrayList<>();
        
        phases.add(new DevOpsConcept(
            "1. Plan",
            "plan",
            "Define requirements, create roadmaps, and plan the development process using agile methodologies.",
            new String[]{"Requirement gathering", "Sprint planning", "Backlog management", "Resource allocation"}
        ));
        
        phases.add(new DevOpsConcept(
            "2. Code",
            "code",
            "Developers write code and use version control to manage changes and collaborate effectively.",
            new String[]{"Code development", "Version control", "Code reviews", "Branching strategies"}
        ));
        
        phases.add(new DevOpsConcept(
            "3. Build",
            "build",
            "Compile code into executable artifacts and package applications with dependencies.",
            new String[]{"Compilation", "Dependency management", "Artifact creation", "Build automation"}
        ));
        
        phases.add(new DevOpsConcept(
            "4. Test",
            "test",
            "Execute automated tests to ensure code quality and catch bugs early in the development cycle.",
            new String[]{"Unit testing", "Integration testing", "Security testing", "Performance testing"}
        ));
        
        phases.add(new DevOpsConcept(
            "5. Release",
            "release",
            "Prepare and approve releases, managing the deployment pipeline and release schedules.",
            new String[]{"Release planning", "Change management", "Approval workflows", "Release notes"}
        ));
        
        phases.add(new DevOpsConcept(
            "6. Deploy",
            "deploy",
            "Deploy applications to production or staging environments using automated deployment tools.",
            new String[]{"Automated deployment", "Blue-green deployment", "Canary releases", "Rollback strategies"}
        ));
        
        phases.add(new DevOpsConcept(
            "7. Operate",
            "operate",
            "Manage and maintain applications in production, ensuring availability and performance.",
            new String[]{"Infrastructure management", "Configuration management", "Scaling", "Patching"}
        ));
        
        phases.add(new DevOpsConcept(
            "8. Monitor",
            "monitor",
            "Continuously monitor application performance, collect metrics, and gather feedback.",
            new String[]{"Performance monitoring", "Log analysis", "Alerting", "User feedback"}
        ));
        
        return phases;
    }

    /**
     * Creates and returns a list of DevOps best practices.
     * 
     * @return list of DevOpsConcept objects representing best practices
     */
    private List<DevOpsConcept> getBestPractices() {
        List<DevOpsConcept> practices = new ArrayList<>();
        
        practices.add(new DevOpsConcept(
            "Automate Everything",
            "automate",
            "Automate repetitive tasks including builds, tests, deployments, and infrastructure provisioning to reduce human error and increase speed.",
            new String[]{"CI/CD pipelines", "Infrastructure automation", "Test automation", "Deployment automation"}
        ));
        
        practices.add(new DevOpsConcept(
            "Shift Left Testing",
            "shiftleft",
            "Move testing earlier in the development cycle to catch defects sooner when they are cheaper to fix.",
            new String[]{"Unit tests", "Static code analysis", "Security scanning", "Early integration tests"}
        ));
        
        practices.add(new DevOpsConcept(
            "Implement GitOps",
            "gitops",
            "Use Git as the single source of truth for declarative infrastructure and applications.",
            new String[]{"Version controlled infrastructure", "Automated sync", "Declarative configuration", "Audit trail"}
        ));
        
        practices.add(new DevOpsConcept(
            "Build Security In (DevSecOps)",
            "devsecops",
            "Integrate security practices throughout the entire DevOps lifecycle rather than treating it as an afterthought.",
            new String[]{"Security scanning", "Vulnerability management", "Compliance automation", "Secure coding"}
        ));
        
        practices.add(new DevOpsConcept(
            "Use Immutable Infrastructure",
            "immutable",
            "Instead of modifying existing infrastructure, replace it with new instances to ensure consistency.",
            new String[]{"Containerization", "Image-based deployments", "Blue-green deployments", "Easy rollbacks"}
        ));
        
        practices.add(new DevOpsConcept(
            "Monitor Everything",
            "monitoreverything",
            "Implement comprehensive monitoring, logging, and observability to gain insights into system behavior.",
            new String[]{"Application metrics", "Infrastructure monitoring", "Log aggregation", "Distributed tracing"}
        ));
        
        practices.add(new DevOpsConcept(
            "Foster Collaboration",
            "collaborate",
            "Break down silos between teams and promote shared responsibility for the entire software lifecycle.",
            new String[]{"Cross-functional teams", "Shared tools", "Common goals", "Blameless postmortems"}
        ));
        
        return practices;
    }
}
