package com.example.kun_uz_.dto.Comment;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class CommentFilterDTO {
    private Integer id;
    private Integer regionId;
    private Integer profileId;
    private Integer articleId;
    private LocalDate createdDateFrom;
    private LocalDate createdDateTo;
}
