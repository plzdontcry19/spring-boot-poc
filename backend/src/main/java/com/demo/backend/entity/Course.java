package com.demo.backend.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
@ToString(exclude = "aliens")
@Table(name = "course")
public class Course {

    @Id
    @Column(name = "id", columnDefinition = "serial4")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private Integer credit;

    @OneToOne(mappedBy = "course")
    private CourseMaterial csourseMaterial;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id", referencedColumnName = "id")
    private Teacher teacher;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "course_alien_mapping", joinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id", nullable = false, columnDefinition = "int4"), inverseJoinColumns = @JoinColumn(name = "alien_id", referencedColumnName = "id", nullable = false, columnDefinition = "int4"))
    private List<Alien> aliens;

    public void addAlien(Alien alien) {
        if (this.aliens == null)
            this.aliens = new ArrayList<>();
        this.aliens.add(alien);
    }
}
