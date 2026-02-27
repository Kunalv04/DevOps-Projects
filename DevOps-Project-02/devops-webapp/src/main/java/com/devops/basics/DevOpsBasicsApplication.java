package com.devops.basics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main Spring Boot Application class for DevOps Basics WebApp.
 * 
 * This application demonstrates DevOps fundamentals through an interactive
 * web interface built with Spring Boot and Thymeleaf.
 * 
 * @author DevOps Team
 * @version 1.0.0
 */
@SpringBootApplication
public class DevOpsBasicsApplication {

    /**
     * Main entry point for the Spring Boot application.
     * 
     * @param args command line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(DevOpsBasicsApplication.class, args);
        System.out.println("========================================");
        System.out.println("  DevOps Basics WebApp Started!");
        System.out.println("  Access at: http://localhost:8080");
        System.out.println("========================================");
    }
}
