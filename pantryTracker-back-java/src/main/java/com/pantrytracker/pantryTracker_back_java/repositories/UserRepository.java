package com.pantrytracker.pantryTracker_back_java.repositories;

import com.pantrytracker.pantryTracker_back_java.models.User;
import java.util.Optional;

public interface UserRepository {

    Optional<User> findById(Long id);

    Optional<User> findByUserName(String userName);

    Optional<User> findByEmail(String email);

    boolean existsByUserName(String userName);

    boolean existsByEmail(String email);

    String getPasswordHashByUserName(String userName);
    String getPasswordHashByEmail(String email);

    String getUserNameByEmail(String email);
    String getEmailByUserName(String userName);

    Long getUserIdByUserName(String userName);
    Long getUserIdByEmail(String email);

    Long save(User user);
}

