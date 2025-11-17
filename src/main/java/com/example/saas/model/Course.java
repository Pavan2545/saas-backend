package com.example.saas.model;

import javax.persistence.*;

@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 5000)
    private String description;

    private String category;

    private Double price;

    private Boolean published = true;

    public Course() {}

    public Course(String title, String description, String category, Double price) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
        this.published = true;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Boolean getPublished() { return published; }
    public void setPublished(Boolean published) { this.published = published; }
}
