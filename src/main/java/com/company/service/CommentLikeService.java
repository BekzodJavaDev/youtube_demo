package com.company.service;



import com.company.entity.CommentEntity;
import com.company.entity.CommentLikeEntity;
import com.company.entity.ProfileEntity;
import com.company.enums.LikeStatus;
import com.company.exp.ItemNotFoundException;
import com.company.repository.CommentLikeRepository;
import com.company.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CommentLikeService {
//    @Autowired
//    private CommentLikeRepository commentLikeRepository;
//    @Autowired
//    private CommentRepository commentRepository;
//
//    public void commentLike(Integer commentId, Integer pId) {
//        likeDislike(commentId, pId,LikeStatus.LIKE);
//    }
//
//    public void commentDisLike(Integer commentId, Integer pId) {
//        likeDislike(commentId, pId, LikeStatus.DISLIKE);
//    }
//
//    private void likeDislike(Integer commentId, Integer pId, LikeStatus status) {
//        Optional<CommentLikeEntity> optional = commentLikeRepository.findExists(commentId, pId);
//        if (optional.isPresent()) {
//            CommentLikeEntity like = optional.get();
//            like.setStatus(status);
//            commentLikeRepository.save(like);
//            return;
//        }
//        boolean articleExists = commentRepository.existsById(commentId);
//        if (!articleExists) {
//            throw new ItemNotFoundException("comment NotFound");
//        }
//
//        CommentLikeEntity like = new CommentLikeEntity();
//        like.setComment(new CommentEntity(commentId));
//        like.setProfile(new ProfileEntity(pId));
//        like.setStatus(status);
//        commentLikeRepository.save(like);
//    }
//
//    public void removeLike(Integer commentId, Integer pId) {
//        commentLikeRepository.delete(commentId, pId);
//    }
//
//
////    public  static CommentLikeDTO getNumLikes(Integer id) {
////        HashMap<Integer, Integer> map = CommentLikeRepository.countLikes(id);
////        CommentLikeDTO dto = new CommentLikeDTO();
////        dto.setLikes(map.get("likes"));
////        dto.setDislikes(map.get("dislikes"));
////        return dto;
////    }
}
