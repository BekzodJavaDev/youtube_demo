package com.company.controller;


import com.company.dto.CommentLikeDTO;
import com.company.dto.VideoLikeDTO;
import com.company.service.ProfileService;
import com.company.service.VideoLikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("/video_like")
@RestController
public class VideoLikeController {

    @Autowired
    private ProfileService profileService;

    @Autowired
    private VideoLikeService videoLikeService;

    @PostMapping("/like")
    public ResponseEntity<Void> like(@RequestBody VideoLikeDTO dto
                                     ) {
        Integer id = profileService.getCurrentUser().getProfile().getId();
        videoLikeService.videoLike(dto.getVideoId(), id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/dislike")
    public ResponseEntity<Void> dislike(@RequestBody VideoLikeDTO dto
                                       ) {
        Integer id = profileService.getCurrentUser().getProfile().getId();
        videoLikeService.videoDisLike(dto.getVideoId(), id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/remove")
    public ResponseEntity<Void> remove(@RequestBody VideoLikeDTO dto
                                       ) {
        Integer id = profileService.getCurrentUser().getProfile().getId();
        videoLikeService.removeLike(dto.getVideoId(), id);
        return ResponseEntity.ok().build();
    }

}
