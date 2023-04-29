package com.example.kun_uz_.controller;

import com.example.kun_uz_.dto.JwtDTO.JwtDTO;
import com.example.kun_uz_.dto.RegionDTO.RegionDTO;
import com.example.kun_uz_.enums.ProfileRole;
import com.example.kun_uz_.exps.MethodNotAllowedException;
import com.example.kun_uz_.service.RegionService;
import com.example.kun_uz_.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/region")
public class RegionController {

      @Autowired
      private RegionService service;
    @PostMapping({"", "/"})
    public ResponseEntity<RegionDTO> create(@RequestBody RegionDTO dto,
                                            @RequestHeader("Authorization") String authorization) {
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization, ProfileRole.ADMIN);
        return ResponseEntity.ok(service.create(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<RegionDTO> update(@PathVariable("id") Integer id, @RequestBody RegionDTO dto,
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
       return ResponseEntity.ok(service.getAll(page,size));
    }


}
