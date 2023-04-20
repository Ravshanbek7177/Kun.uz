package com.example.kun_uz_.service;

import com.example.kun_uz_.dto.ArticleTureDTO;
import com.example.kun_uz_.entity.ArticleTureEntity;
import com.example.kun_uz_.exps.AppBadRequestException;
import com.example.kun_uz_.repository.ArticleTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
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

    public boolean delete(Integer id) {
        Optional<ArticleTureEntity> optional = repository.findById(id);
        if(optional.isEmpty()){
            throw new AppBadRequestException("yo'qku");
        }
       repository.delete(optional.get());
        return true;
    }

    public Page<ArticleTureDTO> list(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC,"createdDate");
        Pageable pageable = PageRequest.of(page-1,size,sort);

        Page<ArticleTureEntity> pageObj = repository.findAll(pageable);
        Long totalCount = pageObj.getTotalElements();

        List<ArticleTureEntity> articleTureEntities = pageObj.getContent();
        List<ArticleTureDTO> list = new LinkedList<>();

        for(ArticleTureEntity entity: articleTureEntities){
            ArticleTureDTO dto = (ArticleTureDTO) pageObj.get();
            dto.setNameUZ(entity.getNameUZ());
            dto.setNameRU(entity.getNameRU());
            dto.setNameEN(entity.getNameEN());
           // dto.setVisible(true);
            repository.save(entity);
           list.add(dto);
        }
        Page<ArticleTureDTO> response = new PageImpl<>(list,pageable,totalCount);

       return response;
    }
}
