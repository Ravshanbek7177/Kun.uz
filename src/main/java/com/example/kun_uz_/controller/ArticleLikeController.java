package com.example.kun_uz_.controller;

import com.example.kun_uz_.dto.JwtDTO.JwtDTO;
import com.example.kun_uz_.service.ArticleLikeService;
import com.example.kun_uz_.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/article_like")
public class ArticleLikeController {

    @Autowired
    private ArticleLikeService articleLikeService;

    @GetMapping("/like/{id}")
    public ResponseEntity<Boolean> like(@PathVariable("id") String articleId,
                                        @RequestHeader("Authorization") String authorization) {
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization);
        return ResponseEntity.ok(articleLikeService.like(articleId, jwt.getId()));
    }

    @GetMapping("/dislike/{id}")
    public ResponseEntity<Boolean> dislike(@PathVariable("id") String articleId,
                                           @RequestHeader("Authorization") String authorization) {
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization);
        return ResponseEntity.ok(articleLikeService.dislike(articleId, jwt.getId()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String articleId,
                                          @RequestHeader("Authorization") String authorization) {
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization);
        return ResponseEntity.ok(articleLikeService.delete(articleId, jwt.getId()));
    }

}
