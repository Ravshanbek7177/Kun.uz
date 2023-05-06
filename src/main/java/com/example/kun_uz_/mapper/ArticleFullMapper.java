package com.example.kun_uz_.mapper;

import java.time.LocalDateTime;

public interface ArticleFullMapper {
     String getId();
     String getTitle();
     String getDescription();
     String getContent();
     Long getSharedCount();
     Integer getRegionId();
     String getRegionName();
     Integer getCategoryId();
     String getCategoryName();
     Integer getTypeId();
     String getTypeName();
     LocalDateTime getPublishedDate();
     Long getLikeCount();

     String getAttachId();

}
