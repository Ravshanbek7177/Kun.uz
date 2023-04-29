package com.example.kun_uz_.controller;

import com.example.kun_uz_.dto.Attach.AttachDTO;
import com.example.kun_uz_.dto.JwtDTO.JwtDTO;
import com.example.kun_uz_.dto.ProfileDto.ProfileDTO;
import com.example.kun_uz_.dto.ProfileDto.ProfileFilterDTO;
import com.example.kun_uz_.entity.ProfileEntity;
import com.example.kun_uz_.enums.ProfileRole;
import com.example.kun_uz_.exps.MethodNotAllowedException;
import com.example.kun_uz_.service.ProfileService;
import com.example.kun_uz_.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {   //   BO"LDI
    @Autowired
    private ProfileService profileService;

    @PostMapping({"", "/"})
    public ResponseEntity<ProfileDTO> create(@RequestBody @Valid  ProfileDTO dto
            ,@RequestHeader("Authorization") String authorization) {

        JwtDTO jwt = JwtUtil.getJwtDTO(authorization, ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.create(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProfileDTO> update(@PathVariable("id") Integer id, @RequestBody @Valid ProfileDTO dto,
                                         @RequestHeader("Authorization") String authorization){
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization, ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.update(id,dto));
    }

    @PutMapping("/update2/{id}")
    public ProfileDTO getById(@PathVariable("id") Integer id , @RequestBody @Valid ProfileDTO dto) {
        return ResponseEntity.ok(profileService.update2(id,dto)).getBody();
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<ProfileEntity>> pagination(@RequestParam(value = "page",defaultValue = "1") int page,
                                                          @RequestParam(value = "size",defaultValue = "6") int size,
                                                          @RequestHeader("Authorization") String authorization) {
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization, ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.getAll(page,size));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Integer id,
                                              @RequestHeader("Authorization") String authorization) {
        JwtDTO jwt = JwtUtil.getJwtDTO(authorization, ProfileRole.ADMIN);
        return ResponseEntity.ok(profileService.delete(id));

    }

    @PostMapping("/filter")
    public ResponseEntity<List<ProfileFilterDTO>> getFilter1(@RequestBody @Valid ProfileFilterDTO profileFilterDTO){
        return (ResponseEntity<List<ProfileFilterDTO>>) profileService.getFilter(profileFilterDTO);
    }




}
