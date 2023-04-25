package com.example.kun_uz_.service;

import com.example.kun_uz_.dto.article.ArticleDTO;
import com.example.kun_uz_.entity.Article;
import com.example.kun_uz_.entity.CategoryEntity;
import com.example.kun_uz_.entity.RegionEntity;
import com.example.kun_uz_.enums.ArticleStatus;
import com.example.kun_uz_.exps.AppBadRequestException;
import com.example.kun_uz_.repository.ArticleRepository;
import com.example.kun_uz_.repository.CategoryRepository;
import com.example.kun_uz_.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository repository;
    @Autowired
    private RegionRepository regionRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public ArticleDTO create(ArticleDTO dto) {
        isValidProfile(dto);
        Article article = new Article();
        article.setTitle(dto.getTitle());
        article.setDescription(dto.getDescription());
        article.setContent(dto.getContent());
        article.setShared_count(dto.getShared_count());

        Optional<RegionEntity> optionalRegionEntity = regionRepository.findById(dto.getRegion_id());
        article.setRegion_id(optionalRegionEntity.get());

        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(dto.getCategory_id());
        article.setCategory(optionalCategoryEntity.get());
        article.setArticleStatus(ArticleStatus.NOTPUBLISHED);
        repository.save(article);

        return dto;

    }

    public void isValidProfile(ArticleDTO dto) {
        if (dto.getCategory_id() == null) {
            throw new AppBadRequestException("invalid category");
        }
        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(dto.getCategory_id());
        if (optionalCategoryEntity.isEmpty()) {
            throw new AppBadRequestException("not found category");
        }

        if (dto.getRegion_id() == null) {
            throw new AppBadRequestException("invalid region");
        }
        Optional<RegionEntity> optionalRegionEntity = regionRepository.findById(dto.getRegion_id());
        if (optionalRegionEntity.isEmpty()) {
            throw new AppBadRequestException("not found region");
        }

        if (dto.getDescription() == null) {
            throw new AppBadRequestException("invalid desc");
        }
        if (dto.getShared_count() == null) {
            throw new AppBadRequestException("invalid sharedCount");
        }
        if (dto.getContent() == null) {
            throw new AppBadRequestException("invalid con");
        }
        if (dto.getTitle() == null) {
            throw new AppBadRequestException("invalid title");

        }
    }

    public ArticleDTO update(String id, ArticleDTO dto) {
        Optional<Article> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadRequestException(" yoqku ");
        }
       // isValidProfile(dto);
        Article article = optional.get();
//        article.setId(dto.getId());
        article.setTitle(dto.getTitle());
        article.setDescription(dto.getDescription());
        article.setContent(dto.getContent());
        article.setShared_count(dto.getShared_count());

        Optional<RegionEntity> optionalRegionEntity = regionRepository.findById(dto.getRegion_id());
        article.setRegion_id(optionalRegionEntity.get());

        Optional<CategoryEntity> optionalCategoryEntity = categoryRepository.findById(dto.getCategory_id());
        article.setCategory(optionalCategoryEntity.get());
        article.setArticleStatus(ArticleStatus.NOTPUBLISHED);
        repository.save(article);

        dto.setId(article.getId());
        return dto;
    }

    public Boolean delete(String  id) {
        Optional<Article> optional = repository.findById(id);
        if(optional.isEmpty()){
            throw new AppBadRequestException("yo'qku");
        }
      repository.delete(optional.get());
        return true;
    }

    public boolean update2(String  id, ArticleDTO dto) {
        Optional<Article> optional = repository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadRequestException(" yoqku ");
        }

        if(dto.getArticleStatus().equals(ArticleStatus.NOTPUBLISHED)){
            Article article = optional.get();
            article.setArticleStatus(ArticleStatus.PUBLISHED);
            repository.save(article);
        } else if(dto.getArticleStatus().equals(ArticleStatus.PUBLISHED)){
            Article article = optional.get();
            article.setArticleStatus(ArticleStatus.NOTPUBLISHED);
            repository.save(article);
        }
        return true;
    }

    public Page<Article> getAll(int page, int size) {

        return null;
    }



  /*  public Article get(Integer id) {
        return repository.findById(id).orElseThrow(() -> {
            throw new AppBadRequestException("Profile not found");
        });
    }*/
}
