package io.mountblue.redditclone.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "comment_post_id")
    private long postId;

    @ManyToOne(cascade = CascadeType.ALL)
    private Post post;

    @Column(name = "user_email")
    private String userEmail;

    // Nested Comment area
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_id")
    @JsonBackReference
    private Comment parent;

    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Comment> childComments;
}
