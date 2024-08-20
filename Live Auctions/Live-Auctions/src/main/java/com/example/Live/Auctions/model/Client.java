package com.example.Live.Auctions.model;

import com.example.Live.Auctions.dto.ClientDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.Entity;

import jakarta.persistence.*;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@XmlRootElement(name="client")
@XmlAccessorType(XmlAccessType.FIELD)
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Client extends User {


    @XmlTransient
    @OneToMany(targetEntity = Post.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "idClient", referencedColumnName = "id")
    @NotNull
    private List<Post> postList;

    /*@OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "idClient", referencedColumnName = "id")
    @NotNull
    private List<Comment> commentList;*/

    @NotNull(message = "first name should not be null")
    @Size(min = 5, max = 20)
    @Column
    private String firstName;

    @NotNull
    @Size(min = 3, max = 20)
    @Column
    private String lastName;

    @NotNull
    @Email
    @Column
    private String email;

    @NotNull
    @Column
    private boolean isLoggedIn;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Cascade(value = org.hibernate.annotations.CascadeType.DELETE_ORPHAN)
    @JoinTable(name = "watchlist",
            joinColumns = {
                    @JoinColumn(name = "client_id", referencedColumnName = "id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "product_id", referencedColumnName = "id")
            })
    @JsonIgnore
    @JsonManagedReference
    @XmlTransient
    @NotNull
    private List<Post> posts;

    public Client(String username, String password, List<Post> postList, List<Comment> commentList, String firstName, String lastName, String email) {
        super(username, password);
        this.postList = postList;
        // this.commentList = commentList;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public List<Post> getPostList() {
        return postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public ClientDTO convertToDTO(Client client) {
        ClientDTO clientDTO = new ClientDTO("","");
        clientDTO.setId(client.getId());
        clientDTO.setFirstName(client.getFirstName());
        clientDTO.setLastName(client.getLastName());
        // clientDTO.setCommentDTOList(client.getCommentList());
        clientDTO.setPostDTOList(client.getPostList());
        clientDTO.setPosts(client.getPosts());
        clientDTO.setUsername(client.getUsername());
        clientDTO.setPassword(client.getPassword());
        clientDTO.setEmail(client.getEmail());
        return clientDTO;
    }


}
