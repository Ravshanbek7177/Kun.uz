package com.example.kun_uz_.controller;

import com.example.kun_uz_.dto.JwtDTO.JwtDTO;
import com.example.kun_uz_.enums.ProfileRole;
import com.example.kun_uz_.service.CommentLikeService;
import com.example.kun_uz_.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/commentLike")
public class CommentLikeController {

@Autowired
private CommentLikeService commentLikeService;
    @GetMapping("like/{id}")
    private ResponseEntity<?> like(@PathVariable("id") Integer  commentId,
                                   @RequestHeader("Authorization") String authorization){
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization, ProfileRole.USER);
        return  ResponseEntity.ok(commentLikeService.like(jwt.getId(),commentId));
    }


    @GetMapping("dislike/{id}")
    private ResponseEntity<?> dislike(@PathVariable("id") Integer  commentId,
                                   @RequestHeader("Authorization") String authorization){
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization, ProfileRole.USER);
        return  ResponseEntity.ok(commentLikeService.dislike(jwt.getId(),commentId));
    }

}
