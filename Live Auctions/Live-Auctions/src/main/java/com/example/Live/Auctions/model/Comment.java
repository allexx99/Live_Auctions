package com.example.Live.Auctions.model;

import com.example.Live.Auctions.dto.CommentDTO;
import jakarta.persistence.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column
    @NotNull
    private String text;

    public Comment(String text) {
        this.text = text;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public CommentDTO convertToDTO(Comment comment) {
        CommentDTO commentDTO = new CommentDTO("");
        commentDTO.setId(comment.getId());
        commentDTO.setText(comment.getText());
        return commentDTO;
    }
}
