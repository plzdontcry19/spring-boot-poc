package com.demo.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.demo.backend.entity.Alien;

@Repository
public interface AlienRepository extends JpaRepository<Alien, Long> {

    List<Alien> findByAlienInfomationName(String name);

    List<Alien> findByAlienInfomationNameAndAlienInfomationAge(String name, Integer age);

    // JPQL
    @Query("select alien from Alien alien where alien.alienInfomation.name = :name")
    Alien getAlienByName(@Param("name") String name);

    // Navtive
    @Query(value = "SELECT * FROM alien alien WHERE alien.name = :name", nativeQuery = true)
    List<Alien> getAlienByNameNative(@Param("name") String name);

    @Modifying
    @Transactional
    @Query(value = "UPDATE alien SET name = :name  WHERE age = :age", nativeQuery = true)
    void updateAlienNameByAge(@Param("age") Integer age, @Param("name") String name);

}
