package com.example.saas.controller;

import com.example.saas.model.Course;
import com.example.saas.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<Course>> list() {
        return ResponseEntity.ok(courseService.listPublished());
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN') or hasRole('INSTRUCTOR')")
    public ResponseEntity<List<Course>> listAll() {
        return ResponseEntity.ok(courseService.listAll());
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('INSTRUCTOR')")
    public ResponseEntity<Course> create(@RequestBody Course course) {
        return ResponseEntity.ok(courseService.create(course));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> get(@PathVariable Long id) {
        return courseService.findById(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        courseService.delete(id);
        return ResponseEntity.ok().build();
    }
}
