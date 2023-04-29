package com.example.kun_uz_.controller;

import com.example.kun_uz_.dto.JwtDTO.JwtDTO;
import com.example.kun_uz_.dto.article.ArticleRequestDTO;
import com.example.kun_uz_.entity.ArticleEntity;
import com.example.kun_uz_.enums.ProfileRole;
import com.example.kun_uz_.exps.MethodNotAllowedException;

import com.example.kun_uz_.service.ArticleService;
import com.example.kun_uz_.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/article1")
public class ArticleController {       //    BO"LDI BU
    @Autowired
    private ArticleService service;
  /*  @PostMapping({"","/"})
    public ResponseEntity<?> create(@RequestBody ArticleRequestDTO dto,
                                    @RequestHeader("Authorization") String authorization) {
        String[] str = authorization.split(" ");
        String jwt = str[1];
        JwtDTO jwtDTO = JwtUtil.decode(jwt);
        if (!jwtDTO.getRole().equals(ProfileRole.MODERATOR)) {
            throw new MethodNotAllowedException("Method not allowed");
        }
        return ResponseEntity.ok(service.create(dto));
    }*/

    @PutMapping("/update/{id}")
    public ResponseEntity<ArticleRequestDTO> update(@PathVariable("id") String id, @RequestBody ArticleRequestDTO dto,
                                             @RequestHeader("Authorization") String authorization){
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization, ProfileRole.MODERATOR);
        return ResponseEntity.ok(service.update(id,dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") String  id,
                                                 @RequestHeader("Authorization") String authorization) {
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization, ProfileRole.MODERATOR);
        return ResponseEntity.ok(service.delete(id));

    }

       //    4 chi
    @PutMapping("/update2/{id}")
    public ResponseEntity<Boolean> update2(@PathVariable("id") String  id, @RequestBody ArticleRequestDTO dto,
                                           @RequestHeader("Authorization") String authorization){
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization, ProfileRole.PUBLISHER);
        return ResponseEntity.ok(service.update2(id,dto));
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<ArticleEntity>> pagination(@RequestParam(value = "page",defaultValue = "1") int page,
                                                          @RequestParam(value = "size",defaultValue = "6") int size,
                                                          @RequestHeader("Authorization") String authorization) {
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization, ProfileRole.ADMIN);
        return ResponseEntity.ok(service.getAll(page,size));
    }
    @PostMapping({"","/"})
    public ResponseEntity<ArticleRequestDTO> create(@RequestBody @Valid ArticleRequestDTO dto,
                                                    @RequestHeader("Authorization") String authorization) {
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization, ProfileRole.MODERATOR);
        return ResponseEntity.ok(service.create(dto, jwt.getId()));
    }



}
