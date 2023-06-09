package com.example.kun_uz_.controller;

import com.example.kun_uz_.dto.JwtDTO.JwtDTO;
import com.example.kun_uz_.dto.article.*;
import com.example.kun_uz_.entity.ArticleEntity;
import com.example.kun_uz_.enums.ProfileRole;

import com.example.kun_uz_.service.ArticleService;
import com.example.kun_uz_.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
                                                    HttpServletRequest request){
        JwtUtil.checkForRequiredRole(request, ProfileRole.MODERATOR);
        return ResponseEntity.ok(service.update(id,dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") String  id,
                                              HttpServletRequest request) {
        JwtUtil.checkForRequiredRole(request, ProfileRole.MODERATOR);
        return ResponseEntity.ok(service.delete(id));

    }

       //    4 chi
    @PutMapping("/update2/{id}")
    public ResponseEntity<Boolean> update2(@PathVariable("id") String  id, @RequestBody ChangeStatusDTO dto,
                                           HttpServletRequest request){
        JwtUtil.checkForRequiredRole(request, ProfileRole.PUBLISHER);
        return ResponseEntity.ok(service.update2(id,dto));
    }

    @GetMapping("/pagination")
    public ResponseEntity<Page<ArticleRequestDTO>> pagination(@RequestParam(value = "page",defaultValue = "1") int page,
                                                              @RequestParam(value = "size",defaultValue = "6") int size,
                                                              HttpServletRequest request) {
        JwtUtil.checkForRequiredRole(request, ProfileRole.ADMIN);
        return ResponseEntity.ok(service.getAll(page,size));
    }
    @PostMapping({"","/"})
    public ResponseEntity<ArticleRequestDTO> create(@RequestBody @Valid ArticleRequestDTO dto,
                                                    HttpServletRequest request) {
       JwtUtil.checkForRequiredRole(request, ProfileRole.MODERATOR);
       Integer Pid = (Integer) request.getAttribute("id");
        return ResponseEntity.ok(service.create(dto, Pid));
    }
    @GetMapping("/pagination5/{Id}")
    public ResponseEntity<List<ArticleShortInfo>> pagination5(@PathVariable("Id") Integer id) {
        return ResponseEntity.ok(service.getAll5(id));
    }

    @GetMapping("/pagination3/{Id}")  // 6  boldi
    private ResponseEntity<List<ArticleShortInfo>> pagination3(@PathVariable("Id") String  id){
        return ResponseEntity.ok(service.getAll3(id));
    }

 /*   @GetMapping("/pagination8/{id}")   /// 7
    private ResponseEntity<List<ArticleShortInfo>> pagination8(@PathVariable("Id") String id){
        return ResponseEntity.ok(service.toArticleShortInfo(String ));
    }*/

    @GetMapping("/pagination8")  // 8
    private ResponseEntity<List<ArticleFullDTO>> pagination(@RequestParam("lang") String lang){
        return ResponseEntity.ok(service.getAllLang(lang));
    }

    @GetMapping("/pagination4/{Id}")
    private  ResponseEntity<?> pagination4(@PathVariable("Id") String id){
        return ResponseEntity.ok(service.pagination4(id));
    }

    @GetMapping("/getALL10")
    private ResponseEntity<List<ArticleShortInfo>> getALL10(){
        return ResponseEntity.ok(service.getALL10());
    }




 /*   @GetMapping("/getALL10/{TagName}")
    private ResponseEntity<List<ArticleShortInfo>> getALLTagName(@PathVariable("TagName") String TagName){
        return ResponseEntity.ok(service.getALLTagName(TagName));
    }*/

    @GetMapping("/getALL9/{id}")
 private ResponseEntity<List<ArticleShortInfo>> getALLId(@PathVariable("id") String id) {
        return ResponseEntity.ok(service.getALLId(id));

    }

    @GetMapping("/getALL15/{id}")
    private ResponseEntity<?> getALLPagination(@PathVariable("id") Integer id,
                                               @RequestParam(value = "page",defaultValue = "1") Integer page,
                                                              @RequestParam(value = "size",defaultValue = "10") Integer size
                                                              ) {
        return ResponseEntity.ok(service.getALLPagination(id,page , size));

    }

    @PutMapping("/getALL16/{id}")
    private ResponseEntity<Boolean> getALLCount(@PathVariable("id") String id) {
        return ResponseEntity.ok(service.getALLCount(id));

    }

    @PutMapping("/getALL17/{id}")
    private ResponseEntity<Boolean> getALLShare(@PathVariable("id") String id) {
        return ResponseEntity.ok(service.getALLShare(id));

    }

   @PutMapping("/filter")
    private ResponseEntity<List<ArticleEntity>> getFilter(@RequestParam(value = "page",defaultValue = "1") Integer page,
                                                          @RequestParam(value = "size",defaultValue = "10") Integer size,
                                                          @RequestBody ArticleFilterDTO dto){
        return ResponseEntity.ok(service.getFilter(dto, page,size));
   }



}
