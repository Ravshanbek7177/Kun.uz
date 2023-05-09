package com.example.kun_uz_.controller;

import com.example.kun_uz_.dto.JwtDTO.JwtDTO;
import com.example.kun_uz_.enums.ProfileRole;
import com.example.kun_uz_.service.CommentLikeService;
import com.example.kun_uz_.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
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
                                   HttpServletRequest request){
       JwtUtil.checkForRequiredRole(request, ProfileRole.USER);
       Integer Pid = (Integer) request.getAttribute("id");
        return  ResponseEntity.ok(commentLikeService.like(Pid,commentId));
    }


    @GetMapping("dislike/{id}")
    private ResponseEntity<?> dislike(@PathVariable("id") Integer  commentId,
                                      HttpServletRequest request){
     JwtUtil.checkForRequiredRole(request, ProfileRole.USER);
        Integer Pid = (Integer) request.getAttribute("id");
        return  ResponseEntity.ok(commentLikeService.dislike(Pid,commentId));
    }

}
