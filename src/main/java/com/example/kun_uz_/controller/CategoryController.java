package com.example.kun_uz_.controller;

import com.example.kun_uz_.dto.categoryEntity.CategoryDTO;
import com.example.kun_uz_.dto.JwtDTO.JwtDTO;
import com.example.kun_uz_.enums.ProfileRole;
import com.example.kun_uz_.exps.MethodNotAllowedException;
import com.example.kun_uz_.service.CategoryService;
import com.example.kun_uz_.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    @Autowired
    private CategoryService service;
    @PostMapping({"", "/"})
    public ResponseEntity<CategoryDTO> create(@RequestBody CategoryDTO dto,
                                              @RequestHeader("Authorization") String authorization) {
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization, ProfileRole.ADMIN);
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable("id") Integer id, @RequestBody CategoryDTO dto,
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
