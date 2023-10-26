package io.mountblue.redditclone.restcontroller.post;


import io.mountblue.redditclone.entity.Subreddit;
import io.mountblue.redditclone.repositories.post.SubredditRepository;
import io.mountblue.redditclone.repositories.post.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SubredditController {

    @Autowired
    SubredditRepository subredditRepository;

    @Autowired
    UserRepository userRepository;
    @RequestMapping("/createSubreddit")
    public void addSubreddit(){

        Subreddit subreddit = new Subreddit();
        subreddit.setAdmin(userRepository.getReferenceById(1L));
        subreddit.setName("first subreddit");


        subredditRepository.save(subreddit);
    }
}
