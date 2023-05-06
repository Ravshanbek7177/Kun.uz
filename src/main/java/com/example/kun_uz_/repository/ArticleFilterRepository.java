package com.example.kun_uz_.repository;

import com.example.kun_uz_.dto.article.ArticleFilterDTO;
import com.example.kun_uz_.entity.ArticleEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ArticleFilterRepository {

    @Autowired
    private EntityManager entityManager;


    public List<ArticleEntity> getFilter(ArticleFilterDTO filterDTO , int page , int size) {
        Map<String,Object> params = new HashMap<>();
        StringBuilder builder = new StringBuilder();

        if (filterDTO.getTitle() != null) {
            builder.append(" and s.title like :title");
            params.put("title", "%" + filterDTO.getTitle() + "%");
        }
        if (filterDTO.getRegionId() != null) {
            builder.append(" and s.regionId = :regionId");
            params.put("regionId", filterDTO.getRegionId());
        }

        if (filterDTO.getCategoryId() != null) {
            builder.append(" and s.categoryId = :categoryId");
            params.put("categoryId", filterDTO.getCategoryId());
        }
        if (filterDTO.getTypeId() != null) {
            builder.append(" and s.typeId = :typeId");
            params.put("typeId", filterDTO.getTypeId());
        }
        if (filterDTO.getModeratorId() != null) {
            builder.append(" and s.moderatorId = :moderatorId");
            params.put("moderatorId", filterDTO.getModeratorId());
        }
        if (filterDTO.getPublisherId() != null) {
            builder.append(" and s.publisherId = :publisherId");
            params.put("publisherId", filterDTO.getPublisherId());
        }

        if (filterDTO.getCreatedDateFrom() != null && filterDTO.getCreatedDateTo() != null) {
            builder.append(" and s.createdDate between :dateFrom and dateTo ");
            params.put("dateFrom", LocalDateTime.of(filterDTO.getCreatedDateFrom(), LocalTime.MIN));
            params.put("dateTo", LocalDateTime.of(filterDTO.getCreatedDateTo(), LocalTime.MIN));
        } else if (filterDTO.getCreatedDateFrom() != null) {
            builder.append(" and s.createdDate >= :dateFrom ");
            params.put("dateFrom", LocalDateTime.of(filterDTO.getCreatedDateFrom(), LocalTime.MIN));
        } else if (filterDTO.getCreatedDateTo() != null) {
            builder.append(" and s.createdDate <= :dateTo ");
            params.put("dateTo", LocalDateTime.of(filterDTO.getCreatedDateTo(), LocalTime.MIN));
        }

        StringBuilder selectBuilder = new StringBuilder("Select new ArticleEntity(s.id,s.title,s.description,s.attachId,s.publishedDate) From ArticleEntity as s where visible = true ");
        selectBuilder.append(builder);

        StringBuilder countBuilder = new StringBuilder("select count(s) from ArticleEntity as s where visible = true ");
        countBuilder.append(builder);

        Query selectQuery = this.entityManager.createQuery(selectBuilder.toString());
        Query countQuery = this.entityManager.createQuery(countBuilder.toString());

  for(Map.Entry<String ,Object> map : params.entrySet()){
      selectQuery.setParameter(map.getKey(),map.getValue());
      countQuery.setParameter(map.getKey(),map.getValue());
  }
        selectQuery.setFirstResult((page - 1) * size); // offset
        selectQuery.setMaxResults(size);
        List<ArticleEntity> articleEntityList = selectQuery.getResultList();
        long totalCount = (long) countQuery.getSingleResult();


        return (List<ArticleEntity>) new PageImpl<>(articleEntityList, PageRequest.of(page, size), totalCount);
    }
}
