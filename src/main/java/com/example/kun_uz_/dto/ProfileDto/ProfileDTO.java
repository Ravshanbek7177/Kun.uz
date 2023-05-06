package com.example.kun_uz_.dto.ProfileDto;

import com.example.kun_uz_.entity.AttachEntity;
import com.example.kun_uz_.entity.ProfileEntity;
import com.example.kun_uz_.enums.GeneralStatus;
import com.example.kun_uz_.enums.ProfileRole;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDTO extends ProfileEntity {
    private Integer id;
    @NotBlank(message = "Name  required")
    private String name;
    @NotBlank(message = "Surname required")
    private String surname;;
    @NotNull(message = "Email required")
    private String email;
    @NotBlank(message = "Phone required")
    private String phone;
    @NotBlank(message = "Password required")
    private String password;
    @NotNull(message = "Role required")
    private ProfileRole role;
    @NotNull(message = "Status required")
    private GeneralStatus status;

    private AttachEntity attach;
}
