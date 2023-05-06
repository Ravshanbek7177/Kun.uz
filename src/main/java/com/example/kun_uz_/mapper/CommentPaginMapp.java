package com.example.kun_uz_.mapper;

import java.time.LocalDateTime;


public interface CommentPaginMapp {
    Integer getId();
    LocalDateTime getCreateDate();
    LocalDateTime getUpdateDate();
    Integer getProfileId();
    String getProfileName();
    String getProfileSurname();
    String getArticleId();
    String getArticleTitle();
    Integer getRegionId();

}
