package io.mountblue.redditclone.service.impl;

import io.mountblue.redditclone.entity.Comment;
import io.mountblue.redditclone.entity.Post;
import io.mountblue.redditclone.entity.Subreddit;
import io.mountblue.redditclone.repositories.PostRepository;
import io.mountblue.redditclone.repositories.SubredditRepository;
import io.mountblue.redditclone.service.PostService;
import io.mountblue.redditclone.utils.requests.CreatePostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    private SubredditRepository subredditRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, SubredditRepository subredditRepository){
        this.postRepository = postRepository;
        this.subredditRepository = subredditRepository;
    }
    @Override
    public Page<Post> findAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Post findById(Long id) {
        Optional<Post> findById = postRepository.findById(id);
        if(findById.isEmpty()){
            throw new NoSuchElementException("No post find by id: " + id);
        }
        return findById.get();
    }

    @Override
    public void updateById(Long id, CreatePostRequest createPostRequest) {
        // Only update Title, Content, URL or Image
        Optional<Post> findById = postRepository.findById(id);
        if(findById.isEmpty()){
            throw new NoSuchElementException("No post find by id: " + id);
        }
        Post post = findById.get();
        post.setPostTitle(createPostRequest.getPostTitle());
        post.setPostContent(createPostRequest.getPostContent());
        post.setPostUrl(createPostRequest.getPostUrl());
        post.setImage(createPostRequest.getImage());
        post.setVoteCount(createPostRequest.getVoteCount());
        postRepository.save(post);
    }

    @Override
    public void createPost(CreatePostRequest createPostRequest) {
        Optional<Subreddit> optionalSubreddit = subredditRepository.findById(createPostRequest.getSubredditId());
        if(optionalSubreddit.isEmpty()){
            throw new NoSuchElementException("No subreddit found with id: " + createPostRequest.getSubredditId());
        }
        Subreddit postSubreddit = optionalSubreddit.get();
        Post post = new Post();
        post.setPostTitle(createPostRequest.getPostTitle());
        post.setPostContent(createPostRequest.getPostContent());
//        post.setImage(createPostRequest.getImage());
        post.setImage(null);
        post.setImage(createPostRequest.getImage());

        if(createPostRequest.getFlairs()==null || postSubreddit.getFlair().contains(createPostRequest.getFlairs())) {
            post.setFlairs(createPostRequest.getFlairs()); // setting flairs as a string entered by the user
        }
        else{
          throw new RuntimeException("Flairs Not Allowed by Admin");
        }
        post.setPostUrl(createPostRequest.getPostUrl());
        post.setDraft(createPostRequest.isDraft());
        post.setSubreddit(postSubreddit);
        post.setVoteCount(0);
        post.setCategory(createPostRequest.getCategory());
        post.setVoteCount(0);
        postRepository.save(post);
    }

    @Override
    public void deletePost(Long postId) {
        postRepository.deleteById(postId);
    }

    @Override
    public List<Comment> findCommentsByPostId(Long postId) {
        Optional<Post> postOptional = postRepository.findById(postId);

        if(postOptional.isEmpty()){
            throw new NoSuchElementException("No Comment found for post id: " + postId);
        }

        return postOptional.get().getComments();
    }

    @Override
    public List<Post> searchByString(String searchString) {
        return postRepository.searchMethod(searchString);
    }

    @Override
    public List<Post> findPostsByCategory(String category) {
        // Implement the logic to retrieve posts by the selected category

//        return postRepository.findByCategory(category);
        return postRepository.findByCategoryIgnoreCase(category);
    }

}
