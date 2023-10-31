package io.mountblue.redditclone.restcontroller;

import io.mountblue.redditclone.entity.User;
import io.mountblue.redditclone.service.UserService;
import io.mountblue.redditclone.utils.requests.CreateUserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    private UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerNewUser(@RequestBody CreateUserRequest createUserRequest){
        System.out.println("New User Details: " + createUserRequest);
        try{
            userService.save(createUserRequest);
        }catch(Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();
    }
}
