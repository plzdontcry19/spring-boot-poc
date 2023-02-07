package com.demo.backend.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.backend.entity.Course;
import com.demo.backend.entity.Teacher;

@SpringBootTest
public class CourseRepositoryTest {

    @Autowired
    CourseRepository courseRepository;

    @Test
    public void findAll() {
        List<Course> courses = this.courseRepository.findAll();
        System.out.println("courses: " + courses);
    }

    @Test
    public void saveCourse() {
        Teacher teacher = Teacher.builder().firstname("hey").lastname("heyyyyy").build();
        Course course = Course.builder().title("test").credit(1233).teacher(teacher).build();
        this.courseRepository.save(course);
    }

}
