package com.example.saas.data;

import com.example.saas.model.Course;
import com.example.saas.model.Role;
import com.example.saas.model.User;
import com.example.saas.repository.CourseRepository;
import com.example.saas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataLoader implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (!userRepository.existsByUsername("admin")) {
            User admin = new User("admin", passwordEncoder.encode("Admin@123"), "Admin User");
            admin.setEmail("admin@example.com");
            Set<Role> roles = new HashSet<>();
            roles.add(Role.ROLE_ADMIN);
            admin.setRoles(roles);
            userRepository.save(admin);
            System.out.println("Created admin user -> username: admin password: Admin@123");
        }

        if (courseRepository.count() == 0) {
            courseRepository.save(new Course("Intro to Java", "Beginner friendly Java course.", "Programming", 0.0));
            courseRepository.save(new Course("Spring Boot Basics", "Build REST APIs with Spring Boot.", "Backend", 9.99));
            courseRepository.save(new Course("React for Beginners", "Build modern web UI using React.", "Frontend", 9.99));
            System.out.println("Inserted sample courses.");
        }
    }
}
