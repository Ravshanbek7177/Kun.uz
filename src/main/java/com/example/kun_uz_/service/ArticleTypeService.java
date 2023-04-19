package com.example.kun_uz_.service;

import com.example.kun_uz_.dto.ArticleTureDTO;
import com.example.kun_uz_.entity.ArticleTureEntity;
import com.example.kun_uz_.entity.ProfileEntity;
import com.example.kun_uz_.enums.GeneralStatus;
import com.example.kun_uz_.exps.AppBadRequestException;
import com.example.kun_uz_.repository.ArticleTypeRepository;
import com.example.kun_uz_.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ArticleTypeService {
    @Autowired
    private ArticleTypeRepository repository;
    public ArticleTureDTO create(ArticleTureDTO dto) {


        ArticleTureEntity entity = new ArticleTureEntity();
        entity.setNameUZ(dto.getNameUZ());
        entity.setNameRU(dto.getNameRU());
        entity.setNameEN(dto.getNameEN());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setVisible(true);
        repository.save(entity); // save profile

        dto.setId(entity.getId());
        return dto;

    }

    public void isValidProfile(ArticleTureDTO dto) {
        // throw ...
        Optional<ArticleTureEntity> optional = repository.findById(dto.getId());
        if (optional.isPresent()) {
            throw new AppBadRequestException(" bu foydalanuvchi yoqku");
        }
    }

    public ArticleTureDTO update(Integer id, ArticleTureDTO dto) {
        Optional<ArticleTureEntity> optional = repository.findById(id);
        if(optional.isEmpty()){
            throw new AppBadRequestException("yo'qku");
        }
        ArticleTureEntity entity = optional.get();
        entity.setNameUZ(dto.getNameUZ());
        entity.setNameRU(dto.getNameRU());
        entity.setNameEN(dto.getNameEN());
        entity.setVisible(true);
         repository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }
}
