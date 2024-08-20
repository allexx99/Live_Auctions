package com.example.Live.Auctions.service;

import com.example.Live.Auctions.dto.CommentDTO;
import com.example.Live.Auctions.model.Comment;

import java.util.List;

public interface CommentServiceInterface {

    void addComment(CommentDTO commentDTO);
    List<Comment> getComments();
    Comment updateComment(long id, CommentDTO commentDTO);
    void deleteComment(long id);
}
