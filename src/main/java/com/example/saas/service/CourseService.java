package com.example.saas.service;

import com.example.saas.model.Course;
import com.example.saas.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    public Course create(Course course) {
        return courseRepository.save(course);
    }

    public List<Course> listAll() {
        return courseRepository.findAll();
    }

    public List<Course> listPublished() {
        return courseRepository.findByPublishedTrue();
    }

    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    public void delete(Long id) {
        courseRepository.deleteById(id);
    }
}
