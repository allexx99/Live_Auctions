package com.example.Live.Auctions.dao;

import com.example.Live.Auctions.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostDao extends JpaRepository<Post, Long> {
    Post findPostById(long id);
}
