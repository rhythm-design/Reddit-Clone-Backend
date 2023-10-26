package io.mountblue.redditclone.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@RequiredArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "comment_post_id")
    private long postId;

//    @ManyToOne
//    private Post post;

    @Column(name = "user_email")
    @NonNull
    private String userEmail;

    @Column(name = "comment_data")
    @NonNull
    @JsonManagedReference
    private String commentData;

    // Nested Comment area
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "parent_comment")
    @JsonManagedReference
    private Comment parent;

    @OneToMany(cascade = {
            CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
    }, mappedBy = "parent")
    @JsonManagedReference
    private List<Comment> childComments;

    public void addCommentToParent(Comment comment){
        if(childComments == null){
            childComments = new ArrayList<>();
        }
        comment.setParent(this);
        childComments.add(comment);
    }
}
