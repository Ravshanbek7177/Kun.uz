package com.example.kun_uz_.service;

import com.example.kun_uz_.entity.ArticleLikeEntity;
import com.example.kun_uz_.enums.EmotionStatus;
import com.example.kun_uz_.repository.ArticleLikeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class ArticleLikeService {
    private final ArticleLikeRepository articleLikeRepository;

    public boolean like(String articleId, Integer profileId) {
        makeEmotion(articleId, profileId, EmotionStatus.LIKE);
        return true;
    }

    public boolean dislike(String articleId, Integer profileId) {
        makeEmotion(articleId, profileId, EmotionStatus.DISLIKE);
        return true;
    }

    public boolean delete(String articleId, Integer profileId) {
        articleLikeRepository.delete(articleId, profileId);
        return true;
    }

    private void makeEmotion(String articleId, Integer profileId, EmotionStatus status) {
        Optional<ArticleLikeEntity> optional = articleLikeRepository
                .findByArticleIdAndProfileId(articleId, profileId);
        ArticleLikeEntity articleLikeEntity = optional.get();
        if (optional.isEmpty()) {
            ArticleLikeEntity entity = new ArticleLikeEntity();
            entity.setArticleId(articleId);
            entity.setProfileId(profileId);
            entity.setStatus(status);
            articleLikeRepository.save(entity);
            // article like count dislike count larni trigger orqali qilasizlar.
        } else {
            if(articleLikeEntity.getStatus().equals(EmotionStatus.LIKE)){
                delete(articleId, profileId);
            }
            else if(articleLikeEntity.getStatus().equals(EmotionStatus.DISLIKE)){
                delete(articleId, profileId);
            }
            articleLikeRepository.update(status, articleId, profileId);
        }
    }

}
