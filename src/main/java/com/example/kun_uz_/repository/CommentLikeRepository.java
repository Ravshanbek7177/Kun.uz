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

  @Modifying
  @Transactional
    @Query("update CommentLikeEntity set status =:status where  profileId =:profileId and commentId =:commentId")
  int update(@Param("status") EmotionStatus status,
             @Param("commentId") Integer commentId,
             @Param("profileId") Integer profileId);

    @Modifying
    @Transactional
    @Query("delete from CommentLikeEntity where commentId=:commentId and profileId=:profileId")
    void delete(Integer commentId, Integer profileId);

  Optional<CommentLikeEntity> findByCommentIdAndProfileId(Integer profileId, Integer commentId);
}
