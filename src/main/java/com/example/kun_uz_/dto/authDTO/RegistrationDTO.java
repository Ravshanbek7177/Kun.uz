package com.example.kun_uz_.dto.authDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegistrationDTO {
    @NotBlank(message = "Name required")
    private String name;
    @NotBlank(message = "Surname required")
    private String surname;
    @NotBlank(message = "Email required")
    private String email;
    @NotBlank(message = "Phone required")
    private String phone;
    @NotBlank(message = "Password required")
    private String password;


}
