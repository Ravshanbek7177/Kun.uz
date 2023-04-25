package com.example.kun_uz_.dto.article;

import com.example.kun_uz_.enums.ArticleStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleDTO {
    private String Id;
    private String title;
    private String description;
    private String content;
    private Integer shared_count;
    private Integer image_id;
    private Integer region_id;
    private Integer category_id;
    private Integer moderator_id;
    private Integer publisher_id;
    private Integer view_count;
    private ArticleStatus articleStatus ;

}
