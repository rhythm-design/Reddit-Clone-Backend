package io.mountblue.redditclone.restcontroller;

import io.mountblue.redditclone.entity.Comment;
import io.mountblue.redditclone.service.CommentService;
import io.mountblue.redditclone.utils.requests.CreateCommentRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    private CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/create/{postId}")
    public void createComment(@PathVariable Long postId, @RequestBody CreateCommentRequest createCommentRequest){
        // TODO: Move Path variable to json
        commentService.createComment(postId, createCommentRequest);
    }

    @PostMapping("/reply/{commentId}")
    public void createReply(@PathVariable Long commentId, @RequestBody CreateCommentRequest createCommentRequest){
        // TODO: Move path variable to json
        commentService.createReply(commentId, createCommentRequest);
    }

    @GetMapping("post/{postId}")
    public List<Comment> getCommentsByPostId(@PathVariable Long postId){
        return commentService.findAllCommentsByPostId(postId);
    }

    @GetMapping("/{commentId}")
    public Comment getCommentById(@PathVariable Long commentId){
        return commentService.findCommentById(commentId);
    }

    @PutMapping("/edit/{commentId}")
    public void updateCommentById(@PathVariable Long commentId, CreateCommentRequest createCommentRequest){
        commentService.updateCommentById(commentId, createCommentRequest);
    }

    @DeleteMapping("/delete/{commentId}")
    public void deleteCommentById(@PathVariable Long commentId){
        commentService.deleteCommentById(commentId);
    }
}
