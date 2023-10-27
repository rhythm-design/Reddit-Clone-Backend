package io.mountblue.redditclone.utils.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CreateSubredditRequest {

    private String subredditName;

    private String adminId;

    private Set<String> flairs;
}
