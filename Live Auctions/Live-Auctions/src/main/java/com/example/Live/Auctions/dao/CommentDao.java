package com.example.Live.Auctions.dao;

import com.example.Live.Auctions.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDao extends JpaRepository<Comment, Long> {
}
