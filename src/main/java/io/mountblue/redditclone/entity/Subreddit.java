package io.mountblue.redditclone.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
public class Subreddit {

    @Id
    private long id;

    @OneToMany
    private List<Post> subredditPosts;

//    private Set<String> tags;

    @ManyToOne
    private User admin;

    private boolean communityType;  // 0 for public, 1 for private

    @OneToMany
    private List<User> members;

}
