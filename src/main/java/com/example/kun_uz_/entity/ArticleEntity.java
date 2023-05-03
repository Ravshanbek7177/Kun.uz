
package com.example.kun_uz_.entity;

import com.example.kun_uz_.enums.ArticleStatus;
import com.example.kun_uz_.mapper.ArticleShortInfoMapper;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "article")
public class ArticleEntity {  // bo'ldi
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "title", columnDefinition = "text")
    private String title;
    @Column(name = "description", columnDefinition = "text")
    private String description;
    @Column(name = "content", columnDefinition = "text")
    private String content;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ArticleStatus status = ArticleStatus.NOT_PUBLISHED;
    @Column(name = "shared_count")
    private Integer sharedCount = 0;

    @Column(name = "attach_id")
    private String attachId;
    @ManyToOne
    @JoinColumn(name = "attach_id", insertable = false, updatable = false)
    private AttachEntity attach;

    @Column(name = "region_id")
    private Integer regionId;
    @ManyToOne
    @JoinColumn(name = "region_id", insertable = false, updatable = false)
    private RegionEntity region;

    @Column(name = "category_id")
    private Integer categoryId;
    @ManyToOne
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    private CategoryEntity category;

    @Column(name = "moderator_id")
    private Integer moderatorId;
    @ManyToOne
    @JoinColumn(name = "moderator_id", insertable = false, updatable = false)
    private ProfileEntity moderator;

    @Column(name = "publisher_id")
    private Integer publisherId;
    @ManyToOne
    @JoinColumn(name = "publisher_id", insertable = false, updatable = false)
    private ProfileEntity publisher;

    @Column(name = "type_id")
    private Integer typeId;
    @ManyToOne
    @JoinColumn(name = "type_id", insertable = false, updatable = false)
    private ArticleTypeEntity type;

    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();
    @Column(name = "published_date")
    private LocalDateTime publishedDate;
    @Column(name = "visible")
    private Boolean visible = Boolean.TRUE;
    @Column(name = "view_count")
    private Integer viewCount;
    @Column(name = "article_type")
    private Integer article_type;
    @ManyToOne
    @JoinColumn(name = "tag_Id", insertable = false, updatable = false)
    private TagEntity tagId;

    public ArticleEntity() {

    }

    public ArticleEntity(String id, String title, String description, String attachId, LocalDateTime publishedDate) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.attachId = attachId;
        this.publishedDate = publishedDate;
    }
}

