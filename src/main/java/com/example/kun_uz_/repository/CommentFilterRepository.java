package com.example.kun_uz_.repository;

import com.example.kun_uz_.dto.Comment.CommentFilterDTO;
import com.example.kun_uz_.entity.ArticleEntity;
import com.example.kun_uz_.entity.CommentEntity;
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
public class CommentFilterRepository {
    @Autowired
    private EntityManager entityManager;

    public List<CommentEntity> getFilter(CommentFilterDTO filterDTO, Integer page, Integer size) {
        Map<String,Object> params = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        if (filterDTO.getId() != null) {
            builder.append(" and s.id :id");
            params.put("id", "%" + filterDTO.getId() + "%");
        }
        if (filterDTO.getRegionId() != null) {
            builder.append(" and s.regionId = :regionId");
            params.put("regionId", filterDTO.getRegionId());
        }

        if (filterDTO.getArticleId() != null) {
            builder.append(" and s.articleId = :articleId");
            params.put("articleId", filterDTO.getArticleId());
        }
        if (filterDTO.getProfileId() != null) {
            builder.append(" and s.profileId = :profileId");
            params.put("profileId", filterDTO.getProfileId());
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

        StringBuilder selectBuilder = new StringBuilder("Select new CategoryEntity(s.id,s.createdDate,s.updateDate,s.profileId, s.content , s.articleId,s.replyId ,s.visible) From CategoryEntity as s where visible = true ");
        selectBuilder.append(builder);

        Query selectQuery = this.entityManager.createQuery(selectBuilder.toString());

        for(Map.Entry<String ,Object> map : params.entrySet()){
            selectQuery.setParameter(map.getKey(),map.getValue());
        }

        selectQuery.setFirstResult((page - 1) * size); // offset
        selectQuery.setMaxResults(size);
        List<CommentEntity> commentEntityList = selectQuery.getResultList();

        return  null;   //???? new PageImpl<>(commentEntityList, PageRequest.of(page,size));
    }
}
