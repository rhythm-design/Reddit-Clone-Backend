package io.mountblue.redditclone.repositories;

import io.mountblue.redditclone.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {


    @Query("SELECT p FROM Post p " +
            "LEFT JOIN p.subreddit s " +
            "LEFT JOIN p.user u " +
            "WHERE LOWER(p.postTitle) LIKE %:searchString% " +
            "OR LOWER(p.postContent) LIKE %:searchString% " +
            "OR LOWER(s.name) LIKE %:searchString% " +
            "OR LOWER(u.username) LIKE %:searchString% " +
            "OR LOWER(u.email) LIKE %:searchString% " +
            "OR LOWER(p.flair) LIKE %:searchString%")
    List<Post> searchMethod(@Param("searchString") String searchString);


    List<Post> findByCategoryIgnoreCase(String category);

    List<Post> findByFlairIgnoreCase(String flair);

    Page<Post> findByCreateTimeAfter(Date createTime, Pageable pageable);
}
