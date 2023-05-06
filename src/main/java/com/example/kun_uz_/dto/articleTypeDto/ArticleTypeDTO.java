package com.example.kun_uz_.dto.articleTypeDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleTypeDTO {
    public ArticleTypeDTO() {
    }

    private Integer Id;
    @NotBlank(message = "NameUZ  required")
    private String nameUZ;
    @NotBlank(message = "NameRU  required")
    private String nameRU;
    @NotBlank(message = "NameEN  required")
    private String nameEN;

    public ArticleTypeDTO(String categoryName, String typeName) {

    }
}
