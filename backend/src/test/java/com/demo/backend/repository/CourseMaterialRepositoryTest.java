package com.demo.backend.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.backend.entity.Course;
import com.demo.backend.entity.CourseMaterial;

@SpringBootTest
public class CourseMaterialRepositoryTest {

    @Autowired
    CourseMaterialRepository courseMaterialRepository;

    @Test
    public void saveCourseMaterial() {

        Course course = Course.builder().title("DSA").credit(12).build();
        CourseMaterial courseMaterial = CourseMaterial.builder()
                .url("www.yahoo.com")
                .course(course)
                .build();

        this.courseMaterialRepository.save(courseMaterial);
    }

    @Test
    public void findAll() {
        List<CourseMaterial> courseMaterials = this.courseMaterialRepository.findAll();
        System.out.println(courseMaterials);
    }

}
