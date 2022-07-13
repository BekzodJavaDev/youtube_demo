package com.company.controller;


import com.company.dto.video.VideoCreateDTO;
import com.company.dto.video.VideoDTO;

import com.company.service.ProfileService;
import com.company.service.VideoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;


@Slf4j
@RestController
@RequestMapping("/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @Autowired
    private ProfileService profileService;
    @PostMapping("/admin/create")
    public ResponseEntity<VideoDTO> create(@RequestBody @Valid VideoCreateDTO dto) {
        Integer id = profileService.getCurrentUser().getProfile().getId();
        VideoDTO videoDTO = videoService.create(dto, id);
        return ResponseEntity.ok(videoDTO);
    }


    @PutMapping("/admin/{id}")
    public ResponseEntity<String>update(@RequestBody VideoDTO dto,
                                        @PathVariable("id") String id){
        videoService.update(id,dto);
        return ResponseEntity.ok("Successful");
    }


    @DeleteMapping("/admin/delete/{id}")
    public ResponseEntity<String>delete(@PathVariable("id") String id){
        videoService.delete(id);
        return ResponseEntity.ok("Successful");

    }


    @PutMapping("/admin/changeStatus/{id}")
    public ResponseEntity<String>changeStatus(@PathVariable("id") String id){
        Integer pId = profileService.getCurrentUser().getProfile().getId();
        videoService.changeStatus(pId, id);
        return ResponseEntity.ok("Successful");
    }


    @GetMapping("/public/pagination")
    public ResponseEntity<PageImpl> getPagination(@RequestParam(value = "page", defaultValue = "1") int page,
                                                  @RequestParam(value = "size", defaultValue = "5") int size) {
        PageImpl response = videoService.pagination(page, size);
        return ResponseEntity.ok().body(response);
    }






}
