package com.example.kun_uz_.dto.article;

import com.example.kun_uz_.enums.ArticleStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeStatusDTO {
    private ArticleStatus status;
}
