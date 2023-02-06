package com.demo.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(name = "users_email_unique", columnNames = "email")
})
public class User {

    @Id
    @Column(name = "user_id", columnDefinition = "serial4")
    // h2 config
    // @GeneratedValue(strategy = GenerationType.AUTO)
    // postgres id config
    // @SequenceGenerator(sequenceName = "users_user_id_sequence", name =
    // "users_user_id_sequence", allocationSize = 1)
    // @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    private Integer age;

}
