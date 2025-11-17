package com.example.saas.service;

import com.example.saas.model.Course;
import com.example.saas.model.Enrollment;
import com.example.saas.model.User;
import com.example.saas.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    public Enrollment enroll(User user, Course course) {
        Enrollment e = new Enrollment(user, course);
        return enrollmentRepository.save(e);
    }

    public List<Enrollment> findByUserId(Long userId) {
        return enrollmentRepository.findByUserId(userId);
    }
}
