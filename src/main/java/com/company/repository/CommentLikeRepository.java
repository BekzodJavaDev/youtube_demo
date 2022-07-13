package com.company.repository;

import com.company.entity.CommentEntity;
import com.company.entity.CommentLikeEntity;
import com.company.entity.ProfileEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Optional;

public interface CommentLikeRepository extends CrudRepository<CommentLikeEntity,Integer> {
    Optional<CommentLikeEntity> findByArticleAndProfile(CommentEntity comment, ProfileEntity profile);

    @Query("FROM CommentLikeEntity c where  c.comment.id=:commentId and c.profile.id =:profileId")
    Optional<CommentLikeEntity> findExists(Integer commentId, Integer profileId);

    @Transactional
    @Modifying
    @Query("DELETE FROM CommentLikeEntity c where  c.comment.id=:articleId and c.profile.id =:profileId")
    void delete(Integer commentId, Integer profileId);

//    Integer countByStatusAndArticleId(String articleId , LikeStatus status);

//    @Query(value = "SELECT cast(SUM (CASE WHEN t.status = 'LIKE'  THEN 1 ELSE 0 END)as int) AS likes," +
//            "cast(SUM (CASE WHEN t.status = 'DISLIKE'   THEN 1 ELSE 0 END)as int) AS dislikes " +
//            "FROM comment_like t where comment_id = ?1 " ,
//            nativeQuery = true)
//    HashMap<String , Integer> countLikes(Integer id);

}
