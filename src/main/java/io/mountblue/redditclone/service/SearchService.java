package io.mountblue.redditclone.service;

import io.mountblue.redditclone.entity.Post;
import io.mountblue.redditclone.entity.Subreddit;
import org.springframework.data.jpa.domain.Specification;

import java.lang.reflect.Field;

public interface SearchService {

    // All specification should be searched in published posts.
    Specification<Post> searchStringFieldsInPosts(Field[] fields, String text);

    Specification<Subreddit> searchStringFieldsInSubreddit(Field[] fields, String text);

    //TODO: Flair Search Would be implemented in later stages of project
    Specification<String> searchFlairs();

}
