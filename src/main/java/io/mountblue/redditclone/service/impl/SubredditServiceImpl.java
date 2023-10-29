package io.mountblue.redditclone.service.impl;

import io.mountblue.redditclone.entity.Subreddit;
import io.mountblue.redditclone.entity.User;
import io.mountblue.redditclone.repositories.SubredditRepository;
import io.mountblue.redditclone.repositories.UserRepository;
import io.mountblue.redditclone.service.SubredditService;
import io.mountblue.redditclone.utils.requests.CreateSubredditRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service
public class SubredditServiceImpl implements SubredditService {

    private SubredditRepository subredditRepository;

    @Autowired
    private UserRepository userRepository;

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
        subreddit.setFlairs(createSubredditRequest.getFlairs()); // Getting flairs from the user entered data
        // TODO: Update with User object
        subreddit.setAdmin(null); // for now set it null
        subreddit.setSubredditPosts(null);
        subreddit.setMembers(null); // initially members will be null
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

    @Override
    @Transactional
    public void joinSubreddit(Long userId, Long subRedditId) {
        Set<User> currentUserSet = subredditRepository.getReferenceById(subRedditId).getMembers();
        currentUserSet.add(userRepository.getReferenceById(userId)); // This will give us a User who clicked join and add it to the current user list
        subredditRepository.getReferenceById(subRedditId).setMembers(currentUserSet);// Updating the new member posts

        // Not sure about this in many to many mapping , subreddits getting users inmembers but users are not atteached to subredits in joined subreddits
        // so i added this methd and in database the table is showing the data but in postman it has some error
//        Set<Subreddit> currentSubredditList = userRepository.getReferenceById(userId).getJoinedSubreddits();
//        currentSubredditList.add(subredditRepository.getReferenceById(subRedditId));
//        userRepository.getReferenceById(userId).setJoinedSubreddits(currentSubredditList);
    }
}
