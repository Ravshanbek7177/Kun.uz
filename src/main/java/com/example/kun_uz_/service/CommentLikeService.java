package com.example.kun_uz_.service;

import com.example.kun_uz_.dto.CommentLikeDTO.CommentLikeDTO;
import com.example.kun_uz_.entity.CommentEntity;
import com.example.kun_uz_.entity.CommentLikeEntity;
import com.example.kun_uz_.enums.EmotionStatus;
import com.example.kun_uz_.repository.CommentLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentLikeService {

    @Autowired
    private CommentLikeRepository commentLikeRepository;

    public boolean like(Integer profileId, String articleId) {
        makeEmotion(profileId,articleId, EmotionStatus.LIKE);
        return true;
    }
    public boolean dislike(Integer profileId, String articleId){
        makeEmotion(profileId,articleId,EmotionStatus.DISLIKE);
        return true;
    }
    public boolean delete(Integer profileId, String articleId){
        commentLikeRepository.delete(articleId, profileId);
        return true;
    }


    private void makeEmotion(Integer profileId, String articleId, EmotionStatus status) {
        Optional<CommentLikeEntity>  optional = commentLikeRepository.findByArticleIdAndProfileId(profileId,articleId);
        CommentLikeEntity entity = optional.get();
        if(optional.isEmpty()){
      CommentLikeEntity entity1 = new CommentLikeEntity();
      entity1.setArticleId(articleId);
      entity1.setProfileId(profileId);
      entity1.setStatus(EmotionStatus.LIKE);
      commentLikeRepository.save(entity);

        } else {

            if(entity.getStatus().equals(EmotionStatus.LIKE)){
                delete(profileId, articleId);
            }
            if(entity.getStatus().equals(EmotionStatus.DISLIKE)){
                delete(profileId, articleId);
            }
            commentLikeRepository.update(status, articleId, profileId);

        }


    }
}
