package io.mountblue.redditclone.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;



@Getter
@Setter
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

    private String postUrl;

    private Subreddit subreddit;

    private Set<String> tags;

    private boolean isDraft;

    private long voteCount;

}
