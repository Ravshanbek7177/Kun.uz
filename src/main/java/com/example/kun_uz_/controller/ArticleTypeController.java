package com.example.kun_uz_.controller;

import com.example.kun_uz_.dto.articleTypeDto.ArticleTypeDTO;
import com.example.kun_uz_.dto.JwtDTO.JwtDTO;
import com.example.kun_uz_.enums.ProfileRole;
import com.example.kun_uz_.service.ArticleTypeService;
import com.example.kun_uz_.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/article")
public class ArticleTypeController {  // BO"LDI

    @Autowired
    private ArticleTypeService service;

    @PostMapping({"", "/"})
    public ResponseEntity<ArticleTypeDTO> create(@RequestBody @Valid ArticleTypeDTO dto,
                                                 @RequestHeader("Authorization") String authorization) {
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization, ProfileRole.ADMIN);
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ArticleTypeDTO> update(@PathVariable("id") Integer id, @RequestBody @Valid ArticleTypeDTO dto,
                                                 @RequestHeader("Authorization") String authorization){
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization, ProfileRole.ADMIN);
        return ResponseEntity.ok(service.update(id,dto));
    }

    @DeleteMapping("/delete/{id}")
    private ResponseEntity<Boolean> delete(@PathVariable("id") Integer id,
                                           @RequestHeader("Authorization") String authorization){
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization, ProfileRole.ADMIN);
        return ResponseEntity.ok(service.delete(id));
    }

    @GetMapping("/list")
    private ResponseEntity<?> list(@RequestParam(value = "page",defaultValue = "1") int page,
                                   @RequestParam(value = "size",defaultValue = "6") int size,
                                   @RequestHeader("Authorization") String authorization){
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization, ProfileRole.ADMIN);
        return ResponseEntity.ok(service.list(page,size));
    }


}
