package com.demo.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChangePasswordDTO {

    @NotBlank(message = "email is mandatory")
    @Email
    private String email;

    @NotBlank(message = "oldPassword is mandatory")
    private String oldPassword;

    @NotBlank(message = "newPassword is mandatory")
    private String newPassword;

}
