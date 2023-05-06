package com.example.kun_uz_.entity;

import com.example.kun_uz_.dto.Comment.CommentDTO;
import com.example.kun_uz_.mapper.CommentPaginMapp;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "comment")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    @Column(name = "created_Date")
    private LocalDateTime createdDate = LocalDateTime.now();
    @Column(name = "update_Date")
    private LocalDateTime updateDate;
    @Column(name = "profile_id")
    private Integer profileId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id",insertable = false,updatable = false)
    private ProfileEntity profile;
    @Column(name = "content" , columnDefinition = "text")
    private String content;
    @Column(name = "article_id")
    private String articleId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id" , insertable = false,updatable = false)
    private ArticleEntity article;
    @Column(name = "reply_id")
    private Integer replyId;
    @Column(name = "visible")
    private Boolean visible = Boolean.TRUE;

}
