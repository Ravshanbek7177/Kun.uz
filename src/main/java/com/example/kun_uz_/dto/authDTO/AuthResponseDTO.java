package com.example.kun_uz_.dto.authDTO;

import com.example.kun_uz_.enums.ProfileRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponseDTO {
    @NotBlank(message = "Name  required")
    private String name;
    @NotBlank(message = "Surname required")
    private String surname;
    @NotBlank(message = "Role required")
    private ProfileRole role;
    @NotBlank(message = "JWT required")
    private String jwt;
}
