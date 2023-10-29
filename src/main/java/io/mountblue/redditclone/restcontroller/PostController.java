package io.mountblue.redditclone.restcontroller;


import io.mountblue.redditclone.entity.Post;
import io.mountblue.redditclone.entity.Subreddit;
import io.mountblue.redditclone.repositories.PostRepository;
import io.mountblue.redditclone.repositories.SubredditRepository;
import io.mountblue.redditclone.service.PostService;
import io.mountblue.redditclone.service.impl.PostServiceImpl;
import io.mountblue.redditclone.utils.requests.CreatePostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    @PostMapping("/create")
    public String createPost( @RequestBody CreatePostRequest createPostRequest){
        postService.createPost(createPostRequest);
        return "Post created Sucessfully";
    }

    @PostMapping("/posts/{postId}")
    public Post findById(@PathVariable Long postId){
        return postService.findById(postId);
    }

    @GetMapping("/posts")
    public List<Post> getAllPosts(){
        // TODO: This will not display subreddit, because of @JsonBackResponse at Post entity. Solve this issue
        return postService.findAll();
    }

    @GetMapping("/search/{searchString}")
    public List<Post> searchPost(@PathVariable String searchString) {
        return postService.searchByString(searchString);
    }

    @GetMapping("/posts/category")
    public List<Post> getPostsByCategory(@RequestParam("category") String category) {
        return postService.findPostsByCategory(category);
    }

}
