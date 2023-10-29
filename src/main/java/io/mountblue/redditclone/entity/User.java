package io.mountblue.redditclone.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import javax.xml.stream.events.Comment;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "user_email")
    private String email;

    @Column(name = "user_password")
    private String password;

    @Column(name = "user_name")
    private String username;

//    private List<Post> savedPosts;
//
//    private List<Post> upvotedPosts;

    @ManyToMany
    @JsonManagedReference
    private Set<Subreddit> joinedSubreddits;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Subreddit> userCreatedSubReddits;
}
