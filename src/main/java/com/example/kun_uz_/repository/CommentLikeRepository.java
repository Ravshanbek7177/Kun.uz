package com.example.kun_uz_.repository;

import com.example.kun_uz_.entity.CommentLikeEntity;
import com.example.kun_uz_.enums.EmotionStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentLikeRepository extends CrudRepository<CommentLikeEntity,Integer> {

    Optional<CommentLikeEntity> findByArticleIdAndProfileId(Integer profileId, String articleId);
  @Modifying
  @Transactional
    @Query("update CommentEntity set status =:status where  profileId =:profileId and articleId =:articleId")
  int update(@Param("status") EmotionStatus status,
             @Param("articleId") String articleId,
             @Param("profileId") Integer profileId);
}
