package io.mountblue.redditclone.service.impl;

import io.mountblue.redditclone.entity.Subreddit;
import io.mountblue.redditclone.repositories.SubredditRepository;
import io.mountblue.redditclone.service.SubredditService;
import io.mountblue.redditclone.utils.requests.CreateSubredditRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class SubredditServiceImpl implements SubredditService {

    private SubredditRepository subredditRepository;

    @Autowired
    public SubredditServiceImpl(SubredditRepository subredditRepository) {
        this.subredditRepository = subredditRepository;
    }

    @Override
    public Subreddit getSubredditById(Long id) {
        Optional<Subreddit> optionalSubreddit = subredditRepository.findById(id);

        if(optionalSubreddit.isEmpty()){
            throw new NoSuchElementException("No subreddit found by id: " + id);
        }

        return optionalSubreddit.get();
    }

    @Override
    public void deleteSubredditById(Long id) {
        subredditRepository.deleteById(id);
    }

    @Override
    public void createSubreddit(CreateSubredditRequest createSubredditRequest) {
        System.out.println("Request is: " + createSubredditRequest);
        Subreddit subreddit = new Subreddit();
        subreddit.setName(createSubredditRequest.getSubredditName());
        subreddit.setDescription((createSubredditRequest.getSubredditDescription()));
        // TODO: Update with User object
        subreddit.setAdmin(null);
        subreddit.setSubredditPosts(null);
        subreddit.setCommunityType(createSubredditRequest.isSubredditType());
        // TODO: Add tags to new subreddit creation
        subredditRepository.save(subreddit);
    }

    @Override
    public void updateSubreddit(Long id, CreateSubredditRequest createSubredditRequest) {
        Optional<Subreddit> optionalSubreddit = subredditRepository.findById(id);
        if(optionalSubreddit.isEmpty()){
            throw new NoSuchElementException("No Subreddit found for id: " + id);
        }
        Subreddit subreddit = optionalSubreddit.get();
        subreddit.setDescription(createSubredditRequest.getSubredditDescription());
        // TODO: Add tags update here
        subreddit.setCommunityType(createSubredditRequest.isSubredditType());
        subredditRepository.save(subreddit);
    }

    @Override
    public List<Subreddit> findAll() {
        return subredditRepository.findAll();
    }
}
