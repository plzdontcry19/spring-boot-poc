package com.demo.backend.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString(exclude = "courses")
@Table(name = "teacher")
public class Teacher {

    @Id
    @Column(name = "id", columnDefinition = "serial4")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long techerId;

    private String firstname;

    @Column(name = "lastname", columnDefinition = "varchar(64) default NULL")
    private String lastname;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id", columnDefinition = "int4 default NULL")
    private List<Course> courses;
}
