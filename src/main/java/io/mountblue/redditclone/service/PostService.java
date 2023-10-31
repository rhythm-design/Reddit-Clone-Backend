package io.mountblue.redditclone.service;

import io.mountblue.redditclone.entity.Comment;
import io.mountblue.redditclone.entity.Post;
import io.mountblue.redditclone.utils.requests.CreatePostRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {

    Page<Post> findAll(Pageable pageable);

    List<Post> findAll();

    Post findById(Long id);

    void updateById(Long id, CreatePostRequest createPostRequest);

    void createPost(CreatePostRequest createPostRequest);

    void deletePost(Long postId);

    List<Comment> findCommentsByPostId(Long postId);

    List<Post> searchByString(String searchString);

    List<Post> findPostsByCategory(String category);

    List<Post> findPostsByFlair(String flair);

    List<Post> findAllSorted(String sortingOption);

    Page<Post> findAllSortedPaged(String sortingOption, Pageable pageable);
}
