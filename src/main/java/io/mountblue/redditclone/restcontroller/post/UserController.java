package io.mountblue.redditclone.restcontroller.post;

import io.mountblue.redditclone.entity.User;
import io.mountblue.redditclone.repositories.post.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController{

    @Autowired
    UserRepository userRepository;
    @RequestMapping("/addUser")
    public void addUser(){

        User user = new User();
        user.setUsername("tejveer");
        user.setEmail("t@t");
        user.setPassword("1234");

        userRepository.save(user);
    }
}
