package io.mountblue.redditclone.restcontroller.post;


import io.mountblue.redditclone.entity.Post;
import io.mountblue.redditclone.entity.Subreddit;
import io.mountblue.redditclone.repositories.post.PostRepository;
import io.mountblue.redditclone.repositories.post.SubredditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    PostRepository postRepository;

    @Autowired
    SubredditRepository subredditRepository;

    @RequestMapping("/create")
    public String create(){


        String postTitle = "first post";
        String postContent = "hello world";
//        byte[] image = new byte[]{0x12, 0x34, 0x56, 0x78};
        String postUrl = "http://google.com";
        Subreddit subreddit = subredditRepository.getReferenceById(1L);
        boolean isDraft = false;
        long voteCount = 42;

        Post post = new Post();
        post.setPostTitle(postTitle);
        post.setPostContent(postContent);
        post.setImage(null);
        post.setPostUrl(postUrl);
        post.setSubreddit(subreddit);
        post.setDraft(isDraft);
        post.setVoteCount(voteCount);

        postRepository.save(post);

        return "success, post is saved!!!";
    }


    @RequestMapping("/getAll")
    public List<Subreddit> getAll(){
        return subredditRepository.findAll();
    }

}
