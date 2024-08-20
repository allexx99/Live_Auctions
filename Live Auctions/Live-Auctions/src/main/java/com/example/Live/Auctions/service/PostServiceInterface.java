package com.example.Live.Auctions.service;

import com.example.Live.Auctions.dto.PostDTO;
import com.example.Live.Auctions.model.Post;

import java.util.List;

public interface PostServiceInterface {

    void addPost(PostDTO postDTO);
    List<Post> getPosts();
    Post postById(long id);
    Post updatePost(long id, PostDTO postDTO);
    void deletePost(long id);
    boolean newPrice(long postId, int price);
}
