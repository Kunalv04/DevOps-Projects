package com.devops.basics.model;

/**
 * Model class representing a DevOps concept, tool, or practice.
 * 
 * This class encapsulates information about DevOps concepts including
 * their name, identifier, description, and associated benefits or features.
 * 
 * @author DevOps Team
 * @version 1.0.0
 */
public class DevOpsConcept {
    
    private String name;
    private String id;
    private String description;
    private String[] items;

    /**
     * Default constructor.
     */
    public DevOpsConcept() {
    }

    /**
     * Parameterized constructor to create a DevOpsConcept.
     * 
     * @param name the name of the concept
     * @param id the unique identifier for the concept
     * @param description the detailed description of the concept
     * @param items array of benefits, features, or related items
     */
    public DevOpsConcept(String name, String id, String description, String[] items) {
        this.name = name;
        this.id = id;
        this.description = description;
        this.items = items;
    }

    /**
     * Gets the name of the concept.
     * 
     * @return the concept name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the concept.
     * 
     * @param name the concept name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the unique identifier of the concept.
     * 
     * @return the concept identifier
     */
    public String getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the concept.
     * 
     * @param id the concept identifier to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets the description of the concept.
     * 
     * @return the concept description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the concept.
     * 
     * @param description the concept description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the array of items (benefits, features, etc.) associated with the concept.
     * 
     * @return array of items
     */
    public String[] getItems() {
        return items;
    }

    /**
     * Sets the array of items associated with the concept.
     * 
     * @param items the array of items to set
     */
    public void setItems(String[] items) {
        this.items = items;
    }

    /**
     * Returns a formatted string representation of the concept.
     * 
     * @return formatted string with concept details
     */
    @Override
    public String toString() {
        return "DevOpsConcept{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
