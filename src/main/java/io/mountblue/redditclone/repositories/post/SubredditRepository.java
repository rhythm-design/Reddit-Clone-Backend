package io.mountblue.redditclone.repositories.post;

import io.mountblue.redditclone.entity.Post;
import io.mountblue.redditclone.entity.Subreddit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubredditRepository extends JpaRepository<Subreddit,Long> {
}
