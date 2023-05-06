package com.example.kun_uz_.dto.article;

import com.example.kun_uz_.dto.Attach.AttachDTO;
import com.example.kun_uz_.dto.RegionDTO.RegionDTO;
import com.example.kun_uz_.dto.articleTypeDto.ArticleTypeDTO;
import com.example.kun_uz_.dto.categoryDTO.CategoryDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ArticleFullDTO  {
    private String id;
    private String title;
    private String description;
    private String content;
    private Integer sharedCount = 0;
    private RegionDTO region;
    private CategoryDTO category;
    private ArticleTypeDTO articleType;
    private LocalDateTime publishedDate;
    private Integer viewCount;
    private Integer likeCount;
    private AttachDTO image;


}