package io.mountblue.redditclone.restcontroller;

import io.mountblue.redditclone.entity.Subreddit;
import io.mountblue.redditclone.repositories.SubredditRepository;
import io.mountblue.redditclone.service.SubredditService;
import io.mountblue.redditclone.utils.requests.CreateSubredditRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subreddit")
public class SubredditController {

    private SubredditService subredditService;

    @Autowired
    public SubredditController(SubredditService subredditService) {
        this.subredditService = subredditService;
    }


    @PostMapping("/create")
    public String createSubreddit(@RequestBody CreateSubredditRequest createSubredditRequest){
        subredditService.createSubreddit(createSubredditRequest);
        return "Subreddit Created Sucessfully";
    }

    @GetMapping("/get/{id}")
    public Subreddit getSubredditById(@PathVariable Long id){
        return subredditService.getSubredditById(id);
    }


    @PutMapping("/update/{id}")
    public String updateSubreddit(@PathVariable Long id, @RequestBody CreateSubredditRequest createSubredditRequest){
        subredditService.updateSubreddit(id,createSubredditRequest);
        return "Subreddit created sucessfully";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteSubreddit(@PathVariable Long id){
        subredditService.deleteSubredditById(id);
        return "Subreddit deleted sucessfully";
    }

    @GetMapping("/subreddit")
    public List<Subreddit> getSubreddit() {
        return subredditService.findAll();
    }

    @PostMapping("/join/{userId}/{subRedditId}")
    public String joinSubreddit(@PathVariable Long userId ,@PathVariable Long subRedditId){
        subredditService.joinSubreddit(userId,subRedditId);
        return "Member added Successfully";
    }
}
