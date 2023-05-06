package com.example.kun_uz_.mapper;

import com.example.kun_uz_.dto.Comment.CommentDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface CommendMapper {

    Integer getId();
    LocalDateTime getCreateDate();
    LocalDateTime getUpdateDate();
    Integer getProfileId();
    String getProfileName();
    String getProfileSurname();

}
