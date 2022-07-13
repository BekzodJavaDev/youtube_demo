package com.company.service;


import com.company.dto.VideoLikeDTO;
import com.company.entity.ProfileEntity;
import com.company.entity.VideoEntity;
import com.company.entity.VideoLikeEntity;
import com.company.enums.LikeStatus;
import com.company.exp.ItemNotFoundException;

import com.company.repository.VideoLikeRepository;
import com.company.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Optional;

@Service
public class VideoLikeService {
    @Autowired
    private VideoLikeRepository videoLikeRepository;
    @Autowired
    private VideoRepository videoRepository;

    public void videoLike(String videoId, Integer pId) {
        likeDislike(videoId, pId, LikeStatus.LIKE);
    }

    public void videoDisLike(String videoId, Integer pId) {
        likeDislike(videoId, pId, LikeStatus.DISLIKE);
    }

    private void likeDislike(String videoId, Integer pId, LikeStatus status) {
        Optional<VideoLikeEntity> optional = videoLikeRepository.findExists(videoId, pId);
        if (optional.isPresent()) {
            VideoLikeEntity like = optional.get();
            like.setStatus(status);
            videoLikeRepository.save(like);
            return;
        }
        boolean videoExists = videoRepository.existsById(videoId);
        if (!videoExists) {
            throw new ItemNotFoundException("video NotFound");
        }

        VideoLikeEntity like = new VideoLikeEntity();
        like.setVideo(new VideoEntity(videoId));
        like.setProfile(new ProfileEntity(pId));
        like.setStatus(status);
        videoLikeRepository.save(like);
    }

    public void removeLike(String articleId, Integer pId) {

        videoLikeRepository.delete(articleId, pId);
    }

    public VideoLikeDTO getNumLikes(String id) {
        HashMap<String, Integer> map = videoLikeRepository.countLikes(id);
        VideoLikeDTO dto = new VideoLikeDTO();
        dto.setLikes(map.get("likes"));
        dto.setDislikes(map.get("dislikes"));
        return dto;
    }
}
