package com.example.kun_uz_.mapper;

import java.time.LocalDateTime;

public interface ArticleShortInfoMapper {

    String getId();

    String getTitle();

    String getDescription();

    String getAttachId();

    LocalDateTime getPublished_date();
}
