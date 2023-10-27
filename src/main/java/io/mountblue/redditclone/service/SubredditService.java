package io.mountblue.redditclone.service;

import io.mountblue.redditclone.entity.Subreddit;
import io.mountblue.redditclone.utils.requests.CreateSubredditRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubredditService {

    Subreddit getSubredditById(Long id);

    void deleteSubredditById(Long id);

    void createSubreddit(CreateSubredditRequest createSubredditRequest);

    void updateSubreddit(Long id, CreateSubredditRequest createSubredditRequest);

    List<Subreddit> findAll();

}
