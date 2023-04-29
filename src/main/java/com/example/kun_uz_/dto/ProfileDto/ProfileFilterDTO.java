package com.example.kun_uz_.dto.ProfileDto;

import com.example.kun_uz_.enums.GeneralStatus;
import com.example.kun_uz_.enums.ProfileRole;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProfileFilterDTO {
    @NotNull(message = "Id required")
    private Integer id;
    @NotBlank(message = " Name required")
    private String name;
    @NotBlank(message = "Surname required")
    private String surname;
    @NotBlank(message = "Email required")
    private String email;
    @NotBlank(message = "Phone required")
    private String phone;
    @NotNull(message = "Password required")
    private String password;
    private ProfileRole role;
    private GeneralStatus status;
    private LocalDateTime CreatedDateFrom;
    private LocalDateTime CreatedDateTo;

  //  name,surname,phone,role,created_date_from,created_date_to
}
