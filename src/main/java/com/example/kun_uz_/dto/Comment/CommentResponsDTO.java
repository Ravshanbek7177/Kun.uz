package com.example.kun_uz_.dto.Comment;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentResponsDTO {
    private Integer id;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private ProfileResponseDTO profileResponseDTO;
}
