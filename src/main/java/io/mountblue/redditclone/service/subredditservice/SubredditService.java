package io.mountblue.redditclone.service.subredditservice;

import io.mountblue.redditclone.entity.Subreddit;
import io.mountblue.redditclone.repositories.SubredditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SubredditService {

    @Autowired
    SubredditRepository subredditRepository;

    public Subreddit getSubredditById(Long id){
        return subredditRepository.getReferenceById(id);
    }
}
