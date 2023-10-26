package io.mountblue.redditclone.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    private byte[] image;

    @Column(name = "post_url")
    private String postUrl;

    @ManyToOne(cascade = CascadeType.ALL)
    private Subreddit subreddit;

//    private Set<String> tags;

    @Column(name = "is_draft")
    private boolean isDraft;

    @Column(name = "vote_count")
    private long voteCount;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments;

}
