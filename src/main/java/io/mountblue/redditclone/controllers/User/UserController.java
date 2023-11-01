package io.mountblue.redditclone.controllers.User;

import io.mountblue.redditclone.repositories.UserRepository;
import io.mountblue.redditclone.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.*;


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

        if (userRepository.findByEmail(inputUser.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User with the same email already exists");
        }

        User user = new User();
        user.setPassword(inputUser.getPassword());
        user.setUsername(inputUser.getUsername());
        user.setEmail(inputUser.getEmail());
        // User does not exist, so save the new user
        userRepository.save(user);

        return ResponseEntity.ok("User registered successfully");
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

        User loggedInUser = userRepository.findByEmail(inputUser.getEmail()).get();
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
