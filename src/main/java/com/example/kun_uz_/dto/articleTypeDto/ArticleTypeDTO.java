package com.example.kun_uz_.dto.articleTypeDto;

import com.example.kun_uz_.dto.categoryEntity.CategoryDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleTypeDTO extends CategoryDTO {
    private Integer Id;
    private String nameUZ;
    private String nameRU;
    private String nameEN;

}
