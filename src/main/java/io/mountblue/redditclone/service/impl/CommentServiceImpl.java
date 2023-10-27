package io.mountblue.redditclone.service.impl;

import io.mountblue.redditclone.entity.Comment;
import io.mountblue.redditclone.entity.Post;
import io.mountblue.redditclone.repositories.CommentRepository;
import io.mountblue.redditclone.service.CommentService;
import io.mountblue.redditclone.service.PostService;
import io.mountblue.redditclone.utils.requests.CreateCommentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private PostService postService;

    private CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(PostService postService, CommentRepository commentRepository) {
        this.postService = postService;
        this.commentRepository = commentRepository;
    }

    @Override
    public void createComment(Long postId, CreateCommentRequest createCommentRequest) {
        Post post = postService.findById(postId);
        Comment comment = new Comment();
        comment.setParent(null);
        comment.setPost(post);
        comment.setChildComments(null);
        comment.setUserEmail(createCommentRequest.getUserEmail());
        comment.setCommentData(createCommentRequest.getCommentData());
        post.addComment(comment);
        commentRepository.save(comment);
    }

    @Override
    public void createReply(Long commentId, CreateCommentRequest createCommentRequest) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if(optionalComment.isEmpty()){
            throw new NoSuchElementException("No comment found for id: " + commentId);
        }
        Comment parent =optionalComment.get();

        Comment comment = new Comment();
        comment.setParent(parent);
        comment.setCommentData(createCommentRequest.getCommentData());
        comment.setUserEmail(createCommentRequest.getUserEmail());

        parent.addCommentToParent(comment);
        commentRepository.save(parent);
    }

    @Override
    public List<Comment> findAllCommentsByPostId(Long postId) {
        return postService.findCommentsByPostId(postId);
    }

    @Override
    public Comment findCommentById(Long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if(optionalComment.isEmpty()){
            throw new NoSuchElementException("No comment found for id: " + commentId);
        }
        return optionalComment.get();
    }

    @Override
    public void updateCommentById(Long commentId, CreateCommentRequest createCommentRequest) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if(optionalComment.isEmpty()){
            throw new NoSuchElementException("No comment found for id: " + commentId);
        }
        Comment comment = optionalComment.get();
        comment.setCommentData(createCommentRequest.getCommentData());
        commentRepository.save(comment);
    }

    @Override
    public void deleteCommentById(Long commentId) {
        Optional<Comment> optionalComment = commentRepository.findById(commentId);
        if(optionalComment.isEmpty()){
            throw new NoSuchElementException("No comment found for id: " + commentId);
        }
        commentRepository.delete(optionalComment.get());
    }
}
