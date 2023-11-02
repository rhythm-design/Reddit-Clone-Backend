package io.mountblue.redditclone.utils.requests;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CreatePostRequest {

    private String postTitle;

    private String postContent;

    private MultipartFile image;

    private String postUrl;

    private boolean isDraft;

    private Long subredditId;

    private Long voteCount;

    private String category;

    private String flairs;

    private String user;

}
