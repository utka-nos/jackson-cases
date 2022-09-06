package org.example.repo;

import org.example.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepo extends JpaRepository<Post, Long> {
}
