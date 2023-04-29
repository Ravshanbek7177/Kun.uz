package com.example.kun_uz_.controller;

import com.example.kun_uz_.dto.JwtDTO.JwtDTO;
import com.example.kun_uz_.entity.AttachEntity;
import com.example.kun_uz_.enums.ProfileRole;
import com.example.kun_uz_.exps.MethodNotAllowedException;
import com.example.kun_uz_.service.AttachService;
import com.example.kun_uz_.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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
        String fileName = attachService.saveToSystem3(file);
        return ResponseEntity.ok().body(fileName);
    }

 /*   @GetMapping(value = "/open/{fileName}", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] open(@PathVariable("fileName") String fileName) {   //  o'qib oliyotkanda har doyim b
        if (fileName != null && fileName.length() > 0) {
            try {
                return this.attachService.loadImage(fileName);
            } catch (Exception e) {
                e.printStackTrace();
                return new byte[0];
            }
        }
        return null;
    }*/

    @GetMapping(value = "/open/{fileName}" , produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] open(@PathVariable("file") String fileName){
        if(fileName != null && fileName.length()>0){
           try {
               return this.attachService.loadImage(fileName);
           }catch (Exception e){
               e.printStackTrace();
               return new byte[0];
           }
        }
        return null;
    }

    @GetMapping(value = "/open_general/{fileName}", produces = MediaType.ALL_VALUE)
    public byte[] open_general(@PathVariable("fileName") String fileName) {
        return attachService.open_general(fileName);
    }


    @GetMapping("/download/{fineName}")
    public ResponseEntity<Resource> download(@PathVariable("fineName") String fileName) {
        Resource file = (Resource) attachService.download(fileName);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<AttachEntity>> pagination(@RequestParam(value = "page",defaultValue = "1") int page,
                                                         @RequestParam(value = "size",defaultValue = "6") int size) {
  /*      String[] str = authorization.split(" ");
        String jwt = str[1];
        JwtDTO jwtDTO = JwtUtil.decode(jwt);
        if (!jwtDTO.getRole().equals(ProfileRole.ADMIN)) {
            throw new MethodNotAllowedException("Method not allowed");
        }*/
        return ResponseEntity.ok(attachService.getAll(page,size));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") String id){
        return ResponseEntity.ok(attachService.delete(id));
    }


}
