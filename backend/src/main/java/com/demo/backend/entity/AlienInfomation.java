package com.demo.backend.entity;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
@AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "name", nullable = false)),
        @AttributeOverride(name = "species", column = @Column(name = "species", nullable = false)),
        @AttributeOverride(name = "age", column = @Column(name = "age", nullable = false)),
})
public class AlienInfomation {

    private String name;

    private String species;

    private Integer age;
}
