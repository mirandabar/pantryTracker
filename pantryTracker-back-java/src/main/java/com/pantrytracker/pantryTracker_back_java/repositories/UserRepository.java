package com.pantrytracker.pantryTracker_back_java.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.pantrytracker.pantryTracker_back_java.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    User findByUserName(String userName);

    boolean existsByEmail(String email);
    boolean existsByUserName(String userName);
}
