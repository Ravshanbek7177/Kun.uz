package com.example.kun_uz_.dto.ProfileDto;

import com.example.kun_uz_.entity.ProfileEntity;
import com.example.kun_uz_.enums.GeneralStatus;
import com.example.kun_uz_.enums.ProfileRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfileDTO extends ProfileEntity {
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String password;
    private ProfileRole role;
    private GeneralStatus status;
}
