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

    public boolean like(Integer profileId,Integer commentId) {
        makeEmotion(profileId,commentId, EmotionStatus.LIKE);
        return true;
    }
    public boolean dislike(Integer profileId, Integer commentId){
        makeEmotion(profileId,commentId,EmotionStatus.DISLIKE);
        return true;
    }
    public boolean delete(Integer profileId, Integer commentId){
        commentLikeRepository.delete(commentId, profileId);
        return true;
    }


    private void makeEmotion(Integer profileId, Integer commentId, EmotionStatus status) {
        Optional<CommentLikeEntity>  optional = commentLikeRepository.findByCommentIdAndProfileId(profileId,commentId);
       //  CommentLikeEntity entity = optional.get();
        if(optional.isEmpty()){
      CommentLikeEntity entity1 = new CommentLikeEntity();
      entity1.setCommentId(commentId);
      entity1.setProfileId(profileId);
      entity1.setStatus(EmotionStatus.LIKE);
      commentLikeRepository.save(entity1);

        } else {

          /*  if(entity.getStatus().equals(EmotionStatus.LIKE)){
                delete(profileId, commentId);
            }
            if(entity.getStatus().equals(EmotionStatus.DISLIKE)){
                delete(profileId, commentId);
            }*/
            commentLikeRepository.update(status, commentId, profileId);

        }


    }
}
