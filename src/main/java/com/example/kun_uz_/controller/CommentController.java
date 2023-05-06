package com.example.kun_uz_.controller;

import com.example.kun_uz_.dto.Comment.CommentDTO;
import com.example.kun_uz_.dto.Comment.CommentFilterDTO;
import com.example.kun_uz_.dto.Comment.CommentResponsDTO;
import com.example.kun_uz_.entity.CommentEntity;
import com.example.kun_uz_.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @PostMapping({"","/"})
    public ResponseEntity<Object> create(@RequestBody @Valid CommentDTO dto){
        return ResponseEntity.ok(commentService.create(dto));
    }

    @PutMapping({"update/{id}"})
    public ResponseEntity<Object> update(@PathVariable("id") Integer id, @RequestBody @Valid CommentDTO dto){
        return ResponseEntity.ok(commentService.update(id,dto));
    }

    @DeleteMapping({"delete/{id}"})
    public ResponseEntity<Object> delete(@PathVariable("id") Integer id){
        return ResponseEntity.ok(commentService.delete(id));
    }

    @GetMapping("/getAll/{id}")
    private ResponseEntity<List<CommentResponsDTO>> getAll(@PathVariable("id") Integer id){
        return ResponseEntity.ok(commentService.getAll(id));
    }

    @GetMapping("/getPagination")
    private ResponseEntity<Page<CommentDTO>> getPagination(@RequestParam(value = "page",defaultValue = "1") int page,
                                                           @RequestParam(value = "size",defaultValue = "6") int size){  // ,@RequestHeader("Authorization") String authorization
        return  ResponseEntity.ok(commentService.getPagination(page,size));
    }

    @GetMapping("/getPagination")
    private ResponseEntity<List<CommentEntity>> getFilter(@RequestBody CommentFilterDTO dto,
                                                          @RequestParam(value = "page",defaultValue = "1") int page,
                                                          @RequestParam(value = "size",defaultValue = "6") int size){  // ,@RequestHeader("Authorization") String authorization
        return  ResponseEntity.ok(commentService.getFilter(dto,page,size));
    }
    @GetMapping("/getAllREP/{id}")
    private ResponseEntity<List<CommentResponsDTO>> getAllREP(@PathVariable("id") Integer id){
        return ResponseEntity.ok(commentService.getAllREP(id));
    }

}
