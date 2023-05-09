package com.example.kun_uz_.controller;

import com.example.kun_uz_.dto.JwtDTO.JwtDTO;
import com.example.kun_uz_.service.ArticleLikeService;
import com.example.kun_uz_.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
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
                                        HttpServletRequest request) {
         JwtUtil.checkForRequiredRole(request);
         Integer pID = (Integer) request.getAttribute("id");
        return ResponseEntity.ok(articleLikeService.like(articleId, pID));
    }

    @GetMapping("/dislike/{id}")
    public ResponseEntity<Boolean> dislike(@PathVariable("id") String articleId,
                                           HttpServletRequest request) {
        JwtUtil.checkForRequiredRole(request);
        Integer pID = (Integer) request.getAttribute("id");
        return ResponseEntity.ok(articleLikeService.dislike(articleId, pID));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String articleId,
                                          HttpServletRequest request) {
        JwtUtil.checkForRequiredRole(request);
        Integer pID = (Integer) request.getAttribute("id");
        return ResponseEntity.ok(articleLikeService.delete(articleId,pID));
    }

}
