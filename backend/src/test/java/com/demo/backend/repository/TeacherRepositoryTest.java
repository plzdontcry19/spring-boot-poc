package com.demo.backend.repository;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.backend.entity.Course;
import com.demo.backend.entity.Teacher;

@SpringBootTest
public class TeacherRepositoryTest {

    @Autowired
    TeacherRepository teacherRepository;

    @Test
    public void saveCourseMaterial() {

        int random = new Random().nextInt();
        Course jsCourse = Course.builder().title("JS" + random).credit(12).build();
        Course javaCourse = Course.builder().title("JAVA" + random).credit(15).build();
        Teacher teacher = Teacher.builder().firstname("Hey" + random).lastname("hey" + random)
                .courses(List.of(jsCourse, javaCourse))
                .build();
        this.teacherRepository.save(teacher);
    }

}
