package io.mountblue.redditclone.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;
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
    @Transient
    private byte[] image;

    @Column(name = "post_url")
    private String postUrl;

    @ManyToOne(cascade = {
            CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH
    })
    @JsonManagedReference
    private Subreddit subreddit;

    private String flair;

    @Column(name = "is_draft")
    private boolean isDraft;

    @Column(name = "vote_count")
    private long voteCount;

    @Column(name = "create_time")
    private Date createTime;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Comment> comments;

    @Column(name = "category")
    private String category;

    @ManyToOne
    private User user;
    public void addComment(Comment comment){
        if(comments == null){
            comments = new ArrayList<>();
        }
        comment.setPost(this);
        comments.add(comment);
    }
}
