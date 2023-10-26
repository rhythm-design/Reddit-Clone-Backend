package io.mountblue.redditclone.entity;

import jakarta.persistence.*;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "subreddit")
public class Subreddit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToMany(mappedBy = "subreddit", cascade = CascadeType.ALL)
    private List<Post> subredditPosts;

//    private Set<String> tags;

    @ManyToOne
    @JoinColumn(name = "subreddit_admin")
    private User admin;

    @Column(name = "subreddit_access")
    private boolean communityType;  // 0 for public, 1 for private

//    @OneToMany
//    private List<User> members;

}
