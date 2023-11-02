package io.mountblue.redditclone.repositories;

import io.mountblue.redditclone.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    @Query("SELECT u.username FROM User u WHERE u.email = :email")
    String findUsernameByEmail(@Param("email") String email);

}
