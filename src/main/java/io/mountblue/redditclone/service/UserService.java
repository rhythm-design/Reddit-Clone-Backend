package io.mountblue.redditclone.service;

import io.mountblue.redditclone.entity.User;
import io.mountblue.redditclone.utils.requests.CreateUserRequest;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User save(CreateUserRequest createUserRequest);
}
