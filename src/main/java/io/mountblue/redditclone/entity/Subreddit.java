package io.mountblue.redditclone.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.List;
import java.util.Set;

public class Subreddit {

    @Id
    private long id;

    private List<Post> subredditPosts;

    private Set<String> tags;

    private User admin;

    private boolean communityType;  // 0 for public, 1 for private

    private List<User> members;

}
