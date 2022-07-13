package com.company.controller;

import com.company.dto.CommentLikeDTO;
import com.company.service.CommentLikeService;
import com.company.service.ProfileService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Slf4j
@RequestMapping("/comment_like")
@RestController
public class CommentLikeController {
    @Autowired
    private ProfileService profileService;
    @Autowired
    private CommentLikeService commentLikeService;

    @PostMapping("/like")
    public ResponseEntity<Void> like(@RequestBody CommentLikeDTO dto) {
        Integer id = profileService.getCurrentUser().getProfile().getId();
        commentLikeService.commentLike(dto.getCommentId(),id );
        return ResponseEntity.ok().build();
    }

    @PostMapping("/dislike")
    public ResponseEntity<Void> dislike(@RequestBody CommentLikeDTO dto
                                        ) {
        Integer id = profileService.getCurrentUser().getProfile().getId();
        commentLikeService.commentDisLike(dto.getCommentId(),id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/remove")
    public ResponseEntity<Void> remove(@RequestBody CommentLikeDTO dto) {
        Integer id = profileService.getCurrentUser().getProfile().getId();
        commentLikeService.removeLike(dto.getCommentId(),id);
        return ResponseEntity.ok().build();
    }

}
