package io.mountblue.redditclone.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "subreddit")
@Getter @Setter
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
    @JsonIgnore
    private User admin;

    @Column(name = "subreddit_access")
    private String name;

    private boolean communityType;  // 0 for public, 1 for private

//    @OneToMany
//    private List<User> members;

}
