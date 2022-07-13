package com.company.controller;


import com.company.common.ApiResponse;
import com.company.dto.video.VideoLikeDTO;
import com.company.service.ProfileService;
import com.company.service.VideoLikeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<ApiResponse> like(@RequestBody VideoLikeDTO dto
                                     ) {
        Integer id = profileService.getCurrentUser().getProfile().getId();
        videoLikeService.videoLike(dto.getVideoId(), id);
        return new ResponseEntity<>(new ApiResponse(true,"Successfully "), HttpStatus.OK);
    }

    @PostMapping("/dislike")
    public ResponseEntity<ApiResponse> dislike(@RequestBody VideoLikeDTO dto
                                       ) {
        Integer id = profileService.getCurrentUser().getProfile().getId();
        videoLikeService.videoDisLike(dto.getVideoId(), id);
        return new ResponseEntity<>(new ApiResponse(true,"Successfully "), HttpStatus.OK);
    }

    @PostMapping("/remove")
    public ResponseEntity<ApiResponse> remove(@RequestBody VideoLikeDTO dto
                                       ) {
        Integer id = profileService.getCurrentUser().getProfile().getId();
        videoLikeService.removeLike(dto.getVideoId(), id);
        return new ResponseEntity<>(new ApiResponse(true,"Successfully "), HttpStatus.OK);
    }

}
