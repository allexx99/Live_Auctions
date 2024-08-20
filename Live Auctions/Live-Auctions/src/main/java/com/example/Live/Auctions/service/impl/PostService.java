package com.example.Live.Auctions.service.impl;

import com.example.Live.Auctions.config.NotificationEndpoints;
import com.example.Live.Auctions.dao.PostDao;
import com.example.Live.Auctions.dto.PostDTO;
import com.example.Live.Auctions.model.Post;
import com.example.Live.Auctions.service.PostServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PostService implements PostServiceInterface {

    private final PostDao postDao;


    @Autowired
    public PostService(PostDao postDao) {
        this.postDao = postDao;
    }

    public void addPost(PostDTO postDTO) {
        Post post = postDTO.convertToModel(postDTO);
        postDao.save(post);
    }

    public List<Post> getPosts() {
        return postDao.findAll();
    }

    public Post postById(long id) {
        return postDao.findById(id).get();
    }

    // ----------------------------------------------- updatePost -------------------------------------------------- //

    /*public Post updatePost(long id, Post post) {
        Post postToUpdate = postDao.findById(id).get();
        postToUpdate.setTitle(post.getTitle());
        postToUpdate.setDescription(post.getDescription());
        postToUpdate.setCategory(post.getCategory());
        postToUpdate.setBid(post.getBid());
        return postDao.save(postToUpdate);
    }*/

    public Post updatePost(long id, PostDTO postDTO) {
        Post post = postDTO.convertToModel(postDTO);
        Post postToUpdate = postDao.findById(id).get();
        postToUpdate.setTitle(post.getTitle());
        postToUpdate.setDescription(post.getDescription());
        postToUpdate.setCategory(post.getCategory());
        postToUpdate.setBid(post.getBid());
        return postDao.save(postToUpdate);
    }

    // ----------------------------------------------- updatePost -------------------------------------------------- //

    public void deletePost(long id) {
        Post postToDelete = postDao.findById(id).get();
        postDao.delete(postToDelete);
    }

    public boolean newPrice(long postId, int price) {
        Post post = postDao.findPostById(postId);
        System.out.println(post.getTitle() + " has current price " + post.getBid());

        if(price > post.getBid()) {
            post.setBid(price);
            postDao.save(post);
            System.out.println(post.getTitle() + " has this price now: " + price);
            return true;
        }
        else {
            return false;
        }
    }

    public void newTitle(long postId, String newTitle) {
        Post post = postDao.findPostById(postId);
        post.setTitle(newTitle);
        postDao.save(post);
    }

    public void newDescription(long postId, String newDescription) {
        Post post = postDao.findPostById(postId);
        post.setDescription(newDescription);
        postDao.save(post);
    }

    public void newCategory(long postId, String newCategory) {
        Post post = postDao.findPostById(postId);
        post.setCategory(newCategory);
        postDao.save(post);
    }


}
