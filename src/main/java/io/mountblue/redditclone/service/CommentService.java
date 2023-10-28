package io.mountblue.redditclone.service;


import io.mountblue.redditclone.entity.Comment;
import io.mountblue.redditclone.utils.requests.CreateCommentRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    void createComment(Long postId, CreateCommentRequest createCommentRequest);

    void createReply(Long commentId, CreateCommentRequest createCommentRequest);

    List<Comment> findAllCommentsByPostId(Long postId);

    Comment findCommentById(Long commentId);

    void updateCommentById(Long commentId, CreateCommentRequest createCommentRequest);

    void deleteCommentById(Long commentId);

}
