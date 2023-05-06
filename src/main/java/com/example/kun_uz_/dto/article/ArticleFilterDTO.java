package com.example.kun_uz_.dto.article;

import com.example.kun_uz_.enums.ArticleStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ArticleFilterDTO {
    private String title;
    private Integer regionId;
    private Integer categoryId;
    private Integer typeId;
    private LocalDate createdDateFrom;
    private LocalDate createdDateTo;
    private LocalDate publishedDateFrom;
    private LocalDate publishedDateTo;
    private Integer moderatorId;
    private Integer publisherId;
    private ArticleStatus status;
}
