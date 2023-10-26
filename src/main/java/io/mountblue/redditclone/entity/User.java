package io.mountblue.redditclone.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import javax.xml.stream.events.Comment;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class User {

    @Id
    private int id;

    private String email;

    private String password;

    private String username;

    private List<Post> savedPosts;

    private List<Post> upvotedPosts;

    private Set<Subreddit> joinedSubreddits;
}