package com.example.kun_uz_.dto.article;

import com.example.kun_uz_.dto.Attach.AttachDTO;
import com.example.kun_uz_.dto.Attach.AttachRuquestDTO;
import com.example.kun_uz_.mapper.ArticleShortInfoMapper;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ArticleShortInfo {
    @Id  private String id;
    private String title;
    private String description;
    private AttachDTO image;
    private LocalDateTime publishedDate;

}

