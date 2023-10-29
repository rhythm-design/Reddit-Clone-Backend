package io.mountblue.redditclone.controllers.User;

import io.mountblue.redditclone.repositories.UserRepository;
import io.mountblue.redditclone.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @CrossOrigin(origins = {"http://localhost:5173", "http://localhost:8080"})
    @GetMapping("/register")
    public User registerMapping() {
        return new User();
    }


    @CrossOrigin(origins = {"http://localhost:5173", "http://localhost:8080"})
    @PostMapping("/register")
    public ResponseEntity<String> submitRegisterMapping(@RequestBody User inputUser) {

        System.out.println(inputUser.getId());
        System.out.println(inputUser.getEmail());
        System.out.println(inputUser.getUsername());
        System.out.println(inputUser.getUserCreatedSubReddits());

        userRepository.save(inputUser);

        return ResponseEntity.ok("post request to register user");
    }


    @CrossOrigin(origins = {"http://localhost:5173", "http://localhost:8080"})
    @GetMapping("/login")
    public ResponseEntity<String> loginUser() {



        return ResponseEntity.ok("login user...");
    }



        @CrossOrigin(origins = {"http://localhost:5173", "http://localhost:8080"})
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> processLoginUser(@RequestBody User inputUser) {

        System.out.println(inputUser.getEmail());
        System.out.println(inputUser.getPassword());

        User loggedInUser = userRepository.findByEmail(inputUser.getEmail());
        System.out.println(loggedInUser);
        Map<String, Object> responseLogin = new HashMap<>();
        if(loggedInUser != null) {
            responseLogin.put("message", "successfully logged in ...");
            responseLogin.put("loggedIn", true);
        } else {
            responseLogin.put("message", "login with correct credentials !!!!");
            responseLogin.put("loggedIn", false);
        }

        return ResponseEntity.ok(responseLogin);
    }

    @GetMapping("/getAllUsers")
    public List<User> getUsers(){
        return userRepository.findAll();
    }

}
