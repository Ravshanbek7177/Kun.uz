package com.example.kun_uz_.controller;

import com.example.kun_uz_.service.AttachService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/v1/attach")
public class AttachController {
   @Autowired
   private AttachService attachService;

    @PostMapping("/update")
    private ResponseEntity<?> update(@RequestParam("file") MultipartFile file){
        String fileName = attachService.saveToSystem(file);
        return ResponseEntity.ok().body(fileName);
    }

}
