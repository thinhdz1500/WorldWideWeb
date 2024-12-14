package com.thinne.backend.repositories;

import com.thinne.backend.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTableRepository extends JpaRepository<User,Long> {
    User getUserById(long id);
}
