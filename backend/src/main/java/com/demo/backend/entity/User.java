package com.demo.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Entity()
@Table(name = "users")
public class User {

    @Id
    @Column(name = "user_id")
    // h2 config
    // @GeneratedValue(strategy = GenerationType.AUTO)
    // postgres id config
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;

    private Integer age;

}
