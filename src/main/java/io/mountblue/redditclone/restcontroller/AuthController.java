package io.mountblue.redditclone.restcontroller;

import io.mountblue.redditclone.dto.JwtRequest;
import io.mountblue.redditclone.dto.JwtResponse;
import io.mountblue.redditclone.entity.User;
import io.mountblue.redditclone.repositories.UserRepository;
import io.mountblue.redditclone.security.JWTHelper;
import io.mountblue.redditclone.service.UserService;
import io.mountblue.redditclone.utils.requests.CreateUserRequest;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.userdetails.UserDetails;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }


    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager manager;


    @Autowired
    private JWTHelper helper;

    @Autowired
    PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(AuthController.class);


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {
        this.doAuthenticate(request.getEmail(), request.getPassword());


        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

    @PostMapping("/register")
    public ResponseEntity<JwtResponse> registerNewUser(@RequestBody CreateUserRequest createUserRequest){
        System.out.println("New User details: "+ createUserRequest );
        if (userRepository.findByEmail(createUserRequest.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        // User does not exist, so save the new user
        User user = new User();
        user.setPassword(passwordEncoder.encode(createUserRequest.getPassword()));
        user.setUsername(createUserRequest.getUsername());
        user.setEmail(createUserRequest.getEmail());
        userRepository.save(user);
        String token = this.helper.generateToken(user);

        // Sending JWT token at after registring new user so that user don't need to do login
        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(createUserRequest.getEmail()).build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/getUsername")
    public String getUsername(String email){
        return userService.getUsernameByEmail(email);
    }

}
