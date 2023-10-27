package io.mountblue.redditclone.utils.requests;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateCommentRequest {

    private String commentData;

    private String userEmail;

}
