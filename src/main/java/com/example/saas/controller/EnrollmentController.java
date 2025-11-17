package com.example.saas.controller;

import com.example.saas.model.Course;
import com.example.saas.model.Enrollment;
import com.example.saas.model.User;
import com.example.saas.repository.UserRepository;
import com.example.saas.service.CourseService;
import com.example.saas.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseService courseService;

    @PostMapping("/course/{courseId}")
    public ResponseEntity<Enrollment> enroll(@AuthenticationPrincipal UserDetails ud, @PathVariable Long courseId) {
        User user = userRepository.findByUsername(ud.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        Course course = courseService.findById(courseId).orElseThrow(() -> new RuntimeException("Course not found"));
        Enrollment e = enrollmentService.enroll(user, course);
        return ResponseEntity.ok(e);
    }

    @GetMapping("/me")
    public ResponseEntity<List<Enrollment>> myEnrollments(@AuthenticationPrincipal UserDetails ud) {
        User u = userRepository.findByUsername(ud.getUsername()).orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(enrollmentService.findByUserId(u.getId()));
    }
}
