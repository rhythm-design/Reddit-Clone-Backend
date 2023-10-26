package io.mountblue.redditclone.service.PostService;

import io.mountblue.redditclone.entity.Post;
import io.mountblue.redditclone.repositories.post.PostRepository;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    @Autowired
    PostRepository postRepository;

    public Post getPostById(Long id){
        return postRepository.getReferenceById(id);
    }
}
