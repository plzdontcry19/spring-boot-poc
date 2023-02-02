package com.demo.backend.dto;

import org.hibernate.validator.constraints.Length;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UserDTO {

    @NotBlank(message = "Name is mandatory")
    @Length(max = 5)
    private String name;

    @NotNull(message = "Age is mandatory")
    @Positive
    private Integer age;
}
