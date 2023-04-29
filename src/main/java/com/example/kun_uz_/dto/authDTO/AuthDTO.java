package com.example.kun_uz_.dto.authDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthDTO {
    @NotNull(message = "Email required")
    private String email;
    @NotNull(message = "Password required")
    private String password;
}
