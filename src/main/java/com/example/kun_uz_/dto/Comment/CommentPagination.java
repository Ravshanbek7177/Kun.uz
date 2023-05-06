package com.example.kun_uz_.dto.Comment;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class CommentPagination {
    private Integer id;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String content;
    private ProfileResponseDTO profileResponseDTO;
    private ArticleResponseDTO articleResponseDTO;
    private  Integer replyId;

}
