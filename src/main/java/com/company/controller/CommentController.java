package com.company.controller;

import com.company.dto.comment.CommentDTO;
import com.company.service.CommnetService;
import com.company.service.ProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommnetService commnetService;

    @Autowired
    private ProfileService profileService;

    //public

    //secure

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody CommentDTO dto) {
        Integer id = profileService.getCurrentUser().getProfile().getId();
        commnetService.create(dto,id);
        return ResponseEntity.ok(dto.getContent());
    }

    @GetMapping("/{id}")
    public ResponseEntity<String>show(@PathVariable("id") Integer id){
        String  dto = commnetService.get(id);
        return ResponseEntity.ok(dto);

    }
    @GetMapping("/all")
    public ResponseEntity<List<CommentDTO>>show(){
        List<CommentDTO>  dto = commnetService.getAll();
        return ResponseEntity.ok(dto);

    }

    @PutMapping("/{id}")
    public ResponseEntity<String>update(@RequestBody CommentDTO dto,@PathVariable("id") Integer id){
        Integer pId = profileService.getCurrentUser().getProfile().getId();
        commnetService.update(id,dto.getContent(),pId);
        return ResponseEntity.ok("Successful");
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String>delete(@PathVariable("id") Integer id){
        Integer pId = profileService.getCurrentUser().getProfile().getId();
        commnetService.delete(id,pId);
        return ResponseEntity.ok("Successful");

    }




}
