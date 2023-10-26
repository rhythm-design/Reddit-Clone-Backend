package io.mountblue.redditclone.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Comment {

    @Id
    private long id;

    private long postId;

    private User user;

    private Comment parent;

    private List<Comment> childComments;
}
