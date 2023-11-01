package io.mountblue.redditclone.restcontroller;


import io.mountblue.redditclone.entity.Post;
import io.mountblue.redditclone.entity.Subreddit;
import io.mountblue.redditclone.repositories.PostRepository;
import io.mountblue.redditclone.repositories.SubredditRepository;
import io.mountblue.redditclone.service.PostService;
import io.mountblue.redditclone.service.impl.PostServiceImpl;
import io.mountblue.redditclone.utils.ImageUtils;
import io.mountblue.redditclone.utils.requests.CreatePostRequest;
import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@MultipartConfig
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    @PostMapping(value = "/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String createPost(@RequestParam("post-image") MultipartFile file,
                             @RequestParam(value = "postTitle", required = false) String postTitle,
                             @RequestParam(value = "postContent", required = false) String postContent,
                             @RequestParam(value = "postUrl", required = false) String postUrl,
                             @RequestParam(value = "isDraft", required = false) boolean isDraft,
                             @RequestParam(value = "subredditId", required = false) Long subredditId,
                             @RequestParam(value = "voteCount", required = false) Long voteCount,
                             @RequestParam(value = "category", required = false) String category,
                             @RequestParam(value = "flairs", required = false) String flairs
                             ) throws IOException {

        CreatePostRequest createPostRequest = new CreatePostRequest(postTitle, postContent, file,
                                                postUrl, isDraft, subredditId, voteCount, category, flairs,"and@and");
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

    @PutMapping("/update/{postId}")
    public void updatePostById(Long postId, CreatePostRequest createPostRequest){
        postService.updateById(postId, createPostRequest);
    }

    @GetMapping("/post/image/{postId}")
    public ResponseEntity<?> getImageByPostId(@PathVariable Long postId){
        Post post = postService.findById(postId);
        if(post.getImage() != null){
            byte[] imageData = ImageUtils.decompressImage(post.getImage());
            return ResponseEntity.status(HttpStatus.OK)
                    .contentType(MediaType.valueOf("image/png"))
                    .body(imageData);
        }
        return null;
    }

    @GetMapping("/posts/flair")
    public List<Post> getPostsByFlair(@RequestParam("flair") String flair){
        return postService.findPostsByFlair(flair);
    }

    @GetMapping("/posts/sorted")
    public ResponseEntity<Page<Post>> getPostsSorted(
            @RequestParam(name = "sortingOption", required = false) String sortingOption,
            Pageable pageable) {
        Page<Post> posts = postService.findAllSortedPaged(sortingOption,pageable);
        return ResponseEntity.ok(posts);
    }
}
