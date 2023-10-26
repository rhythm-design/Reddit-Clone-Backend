package io.mountblue.redditclone.controllers.User;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/register")
    public ResponseEntity<String> registerMapping() {
        return ResponseEntity.ok("register..");
    }


    @GetMapping("/login")
    public ResponseEntity<String> loginMapping() {
        return ResponseEntity.ok("login..");
    }

}
