package com.example.kun_uz_.dto.articleTypeDto;

import com.example.kun_uz_.dto.categoryEntity.CategoryDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleTypeDTO {

    private Integer Id;
    @NotBlank(message = "NameUZ  required")
    private String nameUZ;
    @NotBlank(message = "NameRU  required")
    private String nameRU;
    @NotBlank(message = "NameEN  required")
    private String nameEN;

}
