package com.example.Live.Auctions.dto;

import com.example.Live.Auctions.model.Client;
import com.example.Live.Auctions.model.Comment;
import com.example.Live.Auctions.model.Post;

import java.util.List;

public class PostDTO {

    private long id;
    private String title;
    private String description;
    private String category;
    private int bid;
    private List<Client> clients;
    private List<Comment> commentList;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getBid() {
        return bid;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public List<Client> getClients() {
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public List<Comment> getComments() {
        return commentList;
    }

    public void setComments(List<Comment> comments) {
        this.commentList = comments;
    }

    public Post convertToModel(PostDTO postDTO) {
        Post post = new Post();
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setCategory(postDTO.getCategory());
        post.setBid(postDTO.getBid());
        post.setClients(postDTO.getClients());
        post.setCommentList(postDTO.getComments());
        return post;
    }
}
