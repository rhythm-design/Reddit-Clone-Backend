package io.mountblue.redditclone.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@Table(name = "post")
public class Post {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "post_title")
    private String postTitle;

    @Column(name = "post_content", columnDefinition = "text")
    private String postContent;

    @Lob
    @Column(name = "post_image")
    @JsonIgnore
    private byte[] image;

    @Column(name = "post_url")
    private String postUrl;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference
    private Subreddit subreddit;

//    private String flairs;

    @Column(name = "is_draft")
    private boolean isDraft;

    @Column(name = "vote_count")
    private long voteCount;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Comment> comments;

    public void addComment(Comment comment){
        if(comments == null){
            comments = new ArrayList<>();
        }
        comment.setPost(this);
        comments.add(comment);
    }

}
