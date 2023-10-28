package io.mountblue.redditclone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class RedditCloneApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedditCloneApplication.class, args);
    }


    @Bean
    public WebMvcConfigurer corsRegister(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedMethods("*")
                        .allowedOrigins("http://localhost:8080", "http://localhost:5173");
                WebMvcConfigurer.super.addCorsMappings(registry);
            }
        };
    }
}
