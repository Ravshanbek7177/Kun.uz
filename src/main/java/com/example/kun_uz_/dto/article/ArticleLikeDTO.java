package com.example.kun_uz_.dto.article;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ArticleLikeDTO {
    @NotNull(message = "article can't be null")
    private String articleId;
}
