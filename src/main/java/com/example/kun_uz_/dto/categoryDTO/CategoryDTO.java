package com.example.kun_uz_.dto.categoryDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CategoryDTO {
    public CategoryDTO(Integer id,  String name) {
        this.Id = id;
        this.name = name;
    }

    private Integer Id;
    @NotNull(message = "NameUZ  required")
    private String nameUZ;
    @NotNull(message = "NameRU  required")
    private String nameRU;
    @NotNull(message = "NameEN  required")
    private String nameEN;
    private LocalDateTime createdDate;
    private Boolean visible;
    private String name;

    public CategoryDTO() {
    }
}


