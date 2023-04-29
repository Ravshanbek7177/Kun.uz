package com.example.kun_uz_.dto.article;

import com.example.kun_uz_.entity.ArticleEntity;
import com.example.kun_uz_.enums.ArticleStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class ArticleRequestDTO {
    @NotNull(message = "title required")
    @Size(min = 10, max = 250 , message = "title must be between 10 and 250 character")
    private String title;
    @NotBlank(message = "File must have some value")
    private String description;
    @NotEmpty(message = "Count qani")
    private String content;
    @NotBlank(message = "Attach required")
    private String attachId;
    @NotNull(message = " Region request ")
    private Integer regionId;
    @NotNull(message = "Category required")
    private Integer categoryId;
    @NotNull(message = "Article type required")
    private Integer typeId;
    private Integer articleType;



    //  @NotEmpty(message = "Should provide value")
    //  private List<Integer> typeList;

}

