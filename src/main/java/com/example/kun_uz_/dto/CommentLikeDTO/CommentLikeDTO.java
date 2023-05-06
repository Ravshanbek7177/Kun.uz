package com.example.kun_uz_.dto.CommentLikeDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentLikeDTO {
    @NotNull(message = "commentId can't be null")
    private String commentId;

}
