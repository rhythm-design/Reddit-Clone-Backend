package io.mountblue.redditclone.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Comment {

    @Id
    private long id;

    @Column(name = "comment_post_id")
    private long postId;

    @ManyToOne
    private Post post;

    private String userEmail;

    // Nested Comment area
    @ManyToOne
    private Comment parent;

    @OneToMany
    private List<Comment> childComments;
}
