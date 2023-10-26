package io.mountblue.redditclone.repositories.post;

import io.mountblue.redditclone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
