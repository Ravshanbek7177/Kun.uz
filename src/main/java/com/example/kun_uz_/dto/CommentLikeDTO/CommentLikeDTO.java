package com.example.kun_uz_.dto.CommentLikeDTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentLikeDTO {
    private Integer Id;
    private Integer profileId;
    private Integer articleId;
    private LocalDateTime createdDate;
   // private

    //     id,profile_id,article_id,created_date,status,
}
