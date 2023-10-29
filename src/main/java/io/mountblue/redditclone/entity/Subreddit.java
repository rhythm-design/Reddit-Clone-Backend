package io.mountblue.redditclone.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonManagedReference
    private List<Post> subredditPosts;

    private String flair; // flairs will be in comma separated string

    @ManyToOne
    @JoinColumn(name = "subreddit_admin")
    @JsonIgnore
    private User admin;

    @Column(name = "subreddit_name")
    private String name;

    @Column(name = "subreddit_description")
    private String description;

    @Column(name = "subreddit_access")
    private boolean communityType;  // 0 for public, 1 for private

    @Column(name = "members")
    @ManyToMany
    @JoinTable(
            name = "subreddit_user",
            joinColumns = @JoinColumn(name = "subreddit_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    @JsonBackReference
    private Set<User> members;

}
