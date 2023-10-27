package io.mountblue.redditclone.service.comment;

import io.mountblue.redditclone.entity.Comment;
import io.mountblue.redditclone.repositories.comment.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/comment")
public class CreateCommentController {

    @Autowired
    CommentRepository commentRepository;


//    @PostMapping("{postId}")
    @PostMapping
    public ResponseEntity<Comment> createComment(){
        boolean temp = true;
        Comment parent = new Comment("email1@example.com", "Word1");
        parent.setParent(null);

        Comment comment2 = new Comment("email2@example.com", "Word2");
        parent.addCommentToParent(comment2);

        Comment comment3 = new Comment("email3@example.com", "Word3");
        parent.addCommentToParent(comment3);

        Comment comment4 = new Comment("email4@example.com", "Word4");
        comment3.addCommentToParent(comment4);

        Comment comment5 = new Comment("email5@example.com", "Word5");
        comment3.addCommentToParent(comment5);

        Comment comment6 = new Comment("email6@example.com", "Word6");
        comment4.addCommentToParent(comment6);

        Comment comment7 = new Comment("email7@example.com", "Word7");
        comment4.addCommentToParent(comment7);

        Comment comment8 = new Comment("email8@example.com", "Word8");
        comment5.addCommentToParent(comment8);

        Comment comment9 = new Comment("email9@example.com", "Word9");
        comment5.addCommentToParent(comment9);

        Comment comment10 = new Comment("email10@example.com", "Word10");
        comment6.addCommentToParent(comment10);


        if(temp){
            commentRepository.save(parent);
//            commentRepository.save(comment2);
//            commentRepository.save(comment3);
//            commentRepository.save(comment4);
//            commentRepository.save(comment5);
//            commentRepository.save(comment6);
//            commentRepository.save(comment7);
//            commentRepository.save(comment8);
//            commentRepository.save(comment9);
//            commentRepository.save(comment10);

        }

        return new ResponseEntity<>(parent, HttpStatus.OK);
    }
}
