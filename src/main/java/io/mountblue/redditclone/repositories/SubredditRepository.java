package io.mountblue.redditclone.repositories;

import io.mountblue.redditclone.entity.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubredditRepository extends JpaRepository<Subreddit, Long> {
}
