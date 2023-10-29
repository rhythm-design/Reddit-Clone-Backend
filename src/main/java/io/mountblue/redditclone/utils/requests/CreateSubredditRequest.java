package io.mountblue.redditclone.utils.requests;

import io.mountblue.redditclone.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@ToString
public class CreateSubredditRequest {

    private String subredditName;

    private String subredditDescription;

    private String admin;

    private String flairs;

    private boolean subredditType;
}
