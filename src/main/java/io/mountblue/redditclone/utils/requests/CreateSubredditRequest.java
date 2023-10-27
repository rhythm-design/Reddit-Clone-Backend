package io.mountblue.redditclone.utils.requests;

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

    private String adminId;

    private Set<String> flairs;

    private boolean subredditType;
}
