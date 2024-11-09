package com.thinne.backend.repositories;

import com.thinne.backend.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepositories extends JpaRepository<Post,Long> {
}
