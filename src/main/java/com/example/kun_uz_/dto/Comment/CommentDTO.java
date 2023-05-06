package com.example.kun_uz_.dto.Comment;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommentDTO {

    private Integer id ;
   // @NotNull(message = "profileID required")
    private Integer profileId;
    @NotBlank(message = "Surname required")
    private String content;
  //  @NotBlank(message = "articleId required")
    private String articleId;
    @NotNull(message = "Surname required")
    private Integer replyId;


}
