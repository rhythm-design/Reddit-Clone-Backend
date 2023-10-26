package io.mountblue.redditclone.restcontroller.post;

import io.mountblue.redditclone.entity.Comment;
import io.mountblue.redditclone.entity.Post;
import io.mountblue.redditclone.entity.Subreddit;
import io.mountblue.redditclone.repositories.post.PostRepository;
import io.mountblue.redditclone.repositories.post.SubRedditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
    PostRepository postRepository;
    @Autowired
    SubRedditRepository subRedditRepository;
    @RequestMapping("/createPost")
    public String addPost(){

        String postTitle = "Fourth Post";
        String postContent = "Sweet Mosquito";
//        byte[] image = new byte[]{0x12, 0x34, 0x56, 0x78};
        String postUrl = "https://example.com/first-post";
        Subreddit subreddit = subRedditRepository.getReferenceById(2L);
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
        return "Success";
    }

    @PostMapping("/getAll")
    public List<Post> getAll(){
        return postRepository.findAll();
    }
}
