package com.demo.backend.repository;

import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.backend.entity.Alien;
import com.demo.backend.entity.AlienInfomation;

@SpringBootTest
public class AlienRepositoryTest {

    @Autowired
    private AlienRepository alienRepository;

    @Test
    public void saveAlien() {
        Random rand = new Random();

        AlienInfomation alienInfomation = AlienInfomation.builder()
                .name("helo" + rand.nextInt())
                .species("hi" + rand.nextInt())
                .age(12)
                .build();
        Alien alien = Alien.builder().alienInfomation(alienInfomation).build();
        this.alienRepository.save(alien);
    }

    @Test
    void testUpdateAlienByAge() {
        this.alienRepository.updateAlienNameByAge(12, "set");
    }
}
