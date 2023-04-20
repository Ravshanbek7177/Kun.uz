package com.example.kun_uz_.controller;

import com.example.kun_uz_.dto.ArticleTureDTO;
import com.example.kun_uz_.dto.JwtDTO;
import com.example.kun_uz_.enums.ProfileRole;
import com.example.kun_uz_.exps.MethodNotAllowedException;
import com.example.kun_uz_.service.ArticleTypeService;
import com.example.kun_uz_.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleTypeController {

    @Autowired
    private ArticleTypeService service;

    @PostMapping({"", "/"})
    public ResponseEntity<ArticleTureDTO> create(@RequestBody ArticleTureDTO dto,
                                             @RequestHeader("Authorization") String authorization) {
        String[] str = authorization.split(" ");
        String jwt = str[1];
        JwtDTO jwtDTO = JwtUtil.decode(jwt);
        if (!jwtDTO.getRole().equals(ProfileRole.ADMIN)) {
            throw new MethodNotAllowedException("Method not allowed");
        }
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ArticleTureDTO> update(@PathVariable("id") Integer id, @RequestBody ArticleTureDTO dto,
                                         @RequestHeader("Authorization") String authorization){
        String[] str = authorization.split(" ");
        String jwt = str[1];
        JwtDTO jwtDTO = JwtUtil.decode(jwt);
        if (!jwtDTO.getRole().equals(ProfileRole.ADMIN)) {
            throw new MethodNotAllowedException("Method not allowed");
        }
        return ResponseEntity.ok(service.update(id,dto));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable("id") Integer id,
                                           @RequestHeader("Authorization") String authorization){
        String[] str = authorization.split(" ");
        String jwt = str[1];
        JwtDTO jwtDTO = JwtUtil.decode(jwt);
        if (!jwtDTO.getRole().equals(ProfileRole.ADMIN)) {
            throw new MethodNotAllowedException("Method not allowed");
        }
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("/list")
    private ResponseEntity<?> list(@RequestParam(value = "page",defaultValue = "1") int page,
                                   @RequestParam(value = "size",defaultValue = "6") int size,
                                   @RequestHeader("Authorization") String authorization){
        String[] sql = authorization.split(" ");
        String jwt = sql[1];
        JwtDTO jwtDTO = JwtUtil.decode(jwt);
        if(!jwtDTO.getRole().equals(ProfileRole.ADMIN)){
            throw new MethodNotAllowedException("Method not allowed");
        }
        return ResponseEntity.ok(service.list(page,size));
    }


}
