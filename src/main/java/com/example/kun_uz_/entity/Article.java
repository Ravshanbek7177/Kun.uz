
package com.example.kun_uz_.entity;

import com.example.kun_uz_.enums.ArticleStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private String id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "content")
    private String content;
    @Column(name = "shared_count")
    private Integer shared_count;
    @Column(name = "image_id")
    private Integer image_id;
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "region_id")
    private RegionEntity region_id;
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "category")
   private CategoryEntity category;
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "moderator_id")
    private ProfileEntity moderator_id;
   @Enumerated(EnumType.STRING)
   @Column(name = "status")
   private ArticleStatus articleStatus ;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
    private ProfileEntity publisher_id;
    @Column(name = "created_Date")
    private LocalDateTime created_Date = LocalDateTime.now();
    @Column(name = "published_date")
    private LocalDateTime published_date = LocalDateTime.now();
    @Column(name = "visible")
    private Boolean visible;
    @Column(name = "visible_count")
    private Integer visible_count;

}

