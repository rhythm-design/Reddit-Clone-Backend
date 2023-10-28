package io.mountblue.redditclone.utils.requests;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CreateUserRequest {

    private String email;

    private String password;

    private String username;
}
