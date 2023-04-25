package com.example.kun_uz_.controller;

import com.example.kun_uz_.dto.EmailHistory.EmailHistoryDTO;
import com.example.kun_uz_.dto.JwtDTO.JwtDTO;
import com.example.kun_uz_.dto.ProfileDto.ProfileDTO;
import com.example.kun_uz_.enums.ProfileRole;
import com.example.kun_uz_.exps.MethodNotAllowedException;
import com.example.kun_uz_.service.EmailHistoryService;
import com.example.kun_uz_.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/emailHistory")
public class EmailHistory {
     @Autowired
     private EmailHistoryService service;
    @PostMapping({"", "/"})
    public ResponseEntity<EmailHistoryDTO> create(@RequestBody EmailHistoryDTO dto) {
        return ResponseEntity.ok(service.create(dto));
    }





}
