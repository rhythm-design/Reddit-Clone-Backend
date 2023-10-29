package io.mountblue.redditclone.utils.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePostRequest {

    private String postTitle;

    private String postContent;

    private byte[] image;

    private String postUrl;

    private boolean isDraft;

    private Long subredditId;

    private long voteCount;

    private String category;

    private String flairs;

}
