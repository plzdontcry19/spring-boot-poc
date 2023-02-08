package com.demo.backend.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SavePasswordDTO {

    @NotBlank(message = "password is mandatory")
    private String password;

}
