package com.example.Live.Auctions.dto;

import com.example.Live.Auctions.model.Client;
import com.example.Live.Auctions.model.Comment;
import com.example.Live.Auctions.model.Post;
import com.example.Live.Auctions.model.User;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class ClientDTO extends UserDTO {

    private List<Post> postList;
    private List<Post> posts; // posts from watchlist
    // private List<Comment> commentList;

    @NotNull
    @Size(min = 5, max = 20)
    private String firstName;
    private String lastName;

    private String email;

    private boolean isLoggedIn = false;

    public ClientDTO(String username, String password) {
        super(username, password);
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Post> getPostDTOList() {
        return postList;
    }

    public void setPostDTOList(List<Post> postList) {
        this.postList = postList;
    }

    // posts form watchlist
    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    /*public List<Comment> getCommentDTOList() {
        return commentList;
    }

    public void setCommentDTOList(List<Comment> commentList) {
        this.commentList = commentList;
    }*/

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Client convertToModel(ClientDTO clientDTO) {
        Client client = new Client();
        client.setPostList(clientDTO.getPostDTOList());
        // client.setCommentList(clientDTO.getCommentDTOList());
        client.setPosts(clientDTO.getPosts());
        client.setFirstName(clientDTO.getFirstName());
        client.setLastName(clientDTO.getLastName());
        client.setUsername(clientDTO.getUsername());
        client.setPassword(clientDTO.getPassword());
        client.setLoggedIn(clientDTO.isLoggedIn());
        client.setEmail(clientDTO.getEmail());
        return client;
    }
}
