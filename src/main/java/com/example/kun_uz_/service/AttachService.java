package com.example.kun_uz_.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class AttachService {


    public String saveToSystem(MultipartFile file) {
     try {
         File folder = new File("attaches");
        if(!folder.exists()){
            folder.mkdirs();
        }
        byte[] bytes = file.getBytes();
         Path path = Paths.get("attaches/"+ file.getOriginalFilename());
         Files.write(path,bytes);
         return file.getOriginalFilename();
     }catch (IOException e){
         e.printStackTrace();
     }
        return null;
    }
}
