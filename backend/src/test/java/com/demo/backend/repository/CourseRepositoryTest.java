package com.demo.backend.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.demo.backend.entity.Alien;
import com.demo.backend.entity.AlienInfomation;
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

        AlienInfomation alienInfomation = AlienInfomation.builder().name("hey").species("heyyy").age(12).build();
        Alien alien = Alien.builder().alienInfomation(alienInfomation).build();
        Course course = Course.builder().title("test").credit(1233).teacher(teacher).build();

        course.addAlien(alien);
        this.courseRepository.save(course);
    }

    @Test
    public void paginationTest() {
        Pageable firstPageThreeRec = PageRequest.of(0, 3);
        Page<Course> coursePages = this.courseRepository.findAll(firstPageThreeRec);
        List<Course> contents = coursePages.getContent();
        long totalEle = coursePages.getTotalElements();
        long totalPage = coursePages.getTotalPages();
        System.out.println("findAllPagination: " + contents);
        System.out.println("contents-size: " + contents.size());
        System.out.println("totalPage: " + totalPage);
        System.out.println("totalEle: " + totalEle);
    }

    @Test
    public void sortingTest() {
        Pageable pageable = PageRequest.of(0, 3, Sort.by("title").ascending());
        Page<Course> coursePages = this.courseRepository.findAll(pageable);
        List<Course> contents = coursePages.getContent();
        System.out.println("sortingTest: " + contents);
        System.out.println("contents-size: " + contents.size());
    }

    @Test
    public void andSortingTest() {
        Pageable pageable = PageRequest.of(0, 3, Sort.by("title").ascending().and(Sort.by("credit")));
        Page<Course> coursePages = this.courseRepository.findAll(pageable);
        List<Course> contents = coursePages.getContent();
        System.out.println("andSortingTest: " + contents);
        System.out.println("contents-size: " + contents.size());
    }

}
