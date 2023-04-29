package com.example.kun_uz_.service;

import com.example.kun_uz_.dto.article.ArticleRequestDTO;
import com.example.kun_uz_.entity.*;
import com.example.kun_uz_.exps.AppBadRequestException;
import com.example.kun_uz_.exps.ItemNotFoundException;
import com.example.kun_uz_.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private final ProfileService profileService ;

    @Autowired
    private final RegionService regionService ;
    @Autowired
    private final CategoryService categoryService ;


    public ArticleRequestDTO create(ArticleRequestDTO dto, Integer moderId) {
        // check
        isValidProfile(dto);
        ArticleEntity entity = new ArticleEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
        entity.setModeratorId(moderId);
        entity.setRegionId(dto.getRegionId());
        entity.setCategoryId(dto.getCategoryId());
        entity.setAttachId(dto.getAttachId());
        // type
        articleRepository.save(entity);
        return dto;
    }

    public void isValidProfile(ArticleRequestDTO dto) {
        if (dto.getAttachId() == null) {
            throw new AppBadRequestException("invalid category");
        }
        if (categoryService.get(dto.getCategoryId())==null) {
            throw new AppBadRequestException("not found category");
        }

        if (dto.getRegionId() == null) {
            throw new AppBadRequestException("invalid region");
        }

        if (regionService.get(dto.getRegionId())==null) {
            throw new AppBadRequestException("not found region");
        }

        if (dto.getDescription() == null) {
            throw new AppBadRequestException("invalid desc");
        }
        if (dto.getContent() == null) {
            throw new AppBadRequestException("invalid con");
        }
        if (dto.getTitle() == null) {
            throw new AppBadRequestException("invalid title");

        }
    }

    public ArticleRequestDTO update(String id, ArticleRequestDTO dto) {

        ArticleEntity entity = get(id);
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
        entity.setRegionId(dto.getRegionId());
        entity.setCategoryId(dto.getCategoryId());
        entity.setAttachId(dto.getAttachId());
        articleRepository.save(entity);
        return dto;
    }
    public ArticleEntity get(String id) {
        Optional<ArticleEntity> optional = articleRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ItemNotFoundException("Item not found: " + id);
        }
        return optional.get();
    }

    public Boolean delete(String  id) {
        Optional<ArticleEntity> optional = articleRepository.findById(id);
        if(optional.isEmpty()){
            throw new AppBadRequestException("yo'qku");
        }
      articleRepository.delete(optional.get());
        return true;
    }

    public boolean update2(String  id, ArticleRequestDTO dto) {
        Optional<ArticleEntity> optional = articleRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadRequestException(" yoqku ");
        }

  /*      if(dto.getStatus().equals(ArticleStatus.NOT_PUBLISHED)){
            ArticleEntity article = optional.get();
            article.setArticleStatus(ArticleStatus.PUBLISHED);
            articleRepository.save(article);
        } else if(dto.getArticleStatus().equals(ArticleStatus.PUBLISHED)){
            ArticleEntity article = optional.get();
            article.setArticleStatus(ArticleStatus.NOT_PUBLISHED);
            articleRepository.save(article);
        }*/
        return true;
    }

    public Page<ArticleEntity> getAll(int page, int size) {

        return null;
    }

  /*  public ArticleDTO create(ArticleDTO dto, Integer moderId) {
        // check
        ProfileEntity moderator = profileService.get(moderId);
        RegionEntity region = regionService.get(dto.getRegionId());
        CategoryEntity category = categoryService.get(dto.getCategoryId());

        ArticleTypeEntity entity = new ArticleTypeEntity();
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
        entity.setModeratorId(moderId);
        entity.setRegionId(dto.getRegionId());
        entity.setCategoryId(dto.getCategoryId());
        entity.setAttachId(dto.getAttachId());
        // type
        articleRepository.save(entity);
        return dto;
    }*/


}
