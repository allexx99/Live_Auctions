package com.example.Live.Auctions.dto;

import com.example.Live.Auctions.model.Comment;
import com.example.Live.Auctions.model.Post;

public class CommentDTO {

    private Long id;
    private String text;

    public Long getId() {
        return id;
    }

    public CommentDTO(String text) {
        this.text = text;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Comment convertToModel(CommentDTO commentDTO) {
        Comment comment = new Comment();
        comment.setText(commentDTO.getText());
        return comment;
    }
}
