package com.example.Live.Auctions.model;

import com.example.Live.Auctions.dao.PostDao;
import com.example.Live.Auctions.dto.PostDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    // image doesn't work
    /*private String imagePath;
    @Column
    BufferedImage productImage = ImageIO.read(new File(imagePath));*/

    @NotBlank
    @NotNull
    @Size(min = 3, max = 20)
    @Column
    private String title;

    @NotBlank
    @NotNull
    @Size(min = 3, max = 100)
    @Column
    private String description;

    @NotBlank
    @NotNull
    @Size(min = 3, max = 20)
    @Column
    private String category;

    @NotBlank
    @NotNull
    @Column
    private int bid;

//    @ManyToOne
//    private Client client;

    @OneToMany(targetEntity = Comment.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "idPost", referencedColumnName = "id")
    @NotNull
    private List<Comment> commentList;

    // for watchlist
    @ManyToMany(mappedBy = "posts")
    @JsonBackReference
    @NotNull
    private List<Client> clients;

    public Post(String title, String description, String category, int bid, List<Comment> commentList) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.bid = bid;
        this.commentList = commentList;
    }


//    public Client getClient() {
//        return client;
//    }
//
//    public void setClient(Client client) {
//        this.client = client;
//    }

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

    public List<Comment> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<Comment> commentList) {
        this.commentList = commentList;
    }

    public PostDTO convertToDTO(Post post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setId(post.getId());
        postDTO.setTitle(post.getTitle());
        postDTO.setDescription(post.getDescription());
        postDTO.setCategory(post.getCategory());
        postDTO.setBid(post.getBid());
        return postDTO;
    }
}
