package com.example.kun_uz_.controller;

import com.example.kun_uz_.dto.JwtDTO;
import com.example.kun_uz_.dto.ProfileDTO;
import com.example.kun_uz_.entity.ProfileEntity;
import com.example.kun_uz_.enums.ProfileRole;
import com.example.kun_uz_.exps.MethodNotAllowedException;
import com.example.kun_uz_.service.ProfileService;
import com.example.kun_uz_.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping({"", "/"})
    public ResponseEntity<ProfileDTO> create(@RequestBody ProfileDTO dto,
                                             @RequestHeader("Authorization") String authorization) {
        String[] str = authorization.split(" ");
        String jwt = str[1];
        JwtDTO jwtDTO = JwtUtil.decode(jwt);
        if (!jwtDTO.getRole().equals(ProfileRole.ADMIN)) {
            throw new MethodNotAllowedException("Method not allowed");
        }
        return ResponseEntity.ok(profileService.create(dto, jwtDTO.getId()));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProfileDTO> update(@PathVariable("id") Integer id, @RequestBody ProfileDTO dto,
                                         @RequestHeader("Authorization") String authorization){
        String[] str = authorization.split(" ");
        String jwt = str[1];
        JwtDTO jwtDTO = JwtUtil.decode(jwt);
        if (!jwtDTO.getRole().equals(ProfileRole.ADMIN)) {
            throw new MethodNotAllowedException("Method not allowed");
        }
        return ResponseEntity.ok(profileService.update(id,dto));
    }

    @PutMapping("/update2/{id}")
    public ProfileDTO getById(@PathVariable("id") Integer id , @RequestBody ProfileDTO dto) {
        return ResponseEntity.ok(profileService.update2(id,dto)).getBody();
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<ProfileEntity>> pagination(@RequestParam(value = "page",defaultValue = "1") int page,
                                                          @RequestParam(value = "size",defaultValue = "6") int size,
                                                          @RequestHeader("Authorization") String authorization) {
        String[] str = authorization.split(" ");
        String jwt = str[1];
        JwtDTO jwtDTO = JwtUtil.decode(jwt);
        if (!jwtDTO.getRole().equals(ProfileRole.ADMIN)) {
            throw new MethodNotAllowedException("Method not allowed");
        }
        return ResponseEntity.ok(profileService.getAll(page,size));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id,
                                              @RequestHeader("Authorization") String authorization) {
        String [] sql = authorization.split(" ");
        String jwt = sql[1];
        JwtDTO jwtDTO = JwtUtil.decode(jwt);
        if (!jwtDTO.getRole().equals(ProfileRole.ADMIN)) {
            throw new MethodNotAllowedException("Method not allowed");
        }
        return ResponseEntity.ok(profileService.delete(id));

    }


}
