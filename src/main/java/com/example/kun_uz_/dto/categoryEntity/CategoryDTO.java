package com.example.kun_uz_.dto.categoryEntity;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryDTO {
    private Integer Id;
    @NotNull(message = "NameUZ  required")
    private String nameUZ;
    @NotNull(message = "NameRU  required")
    private String nameRU;
    @NotNull(message = "NameEN  required")
    private String nameEN;

}
