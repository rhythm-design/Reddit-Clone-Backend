package io.mountblue.redditclone.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "comment")
@RequiredArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "comment_post_id")
    private long postId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Post post;

    @Column(name = "user_email")
    @NonNull
    private String userEmail;

    @Column(name = "comment_data")
    @NonNull
    @JsonManagedReference
    private String commentData;

    // Nested Comment area
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_id")
    @JsonBackReference
    private Comment parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
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
