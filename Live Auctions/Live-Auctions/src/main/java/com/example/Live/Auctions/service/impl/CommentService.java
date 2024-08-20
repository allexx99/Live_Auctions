package com.example.Live.Auctions.service.impl;

import com.example.Live.Auctions.dao.CommentDao;
import com.example.Live.Auctions.dto.CommentDTO;
import com.example.Live.Auctions.model.Comment;
import com.example.Live.Auctions.service.CommentServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CommentService implements CommentServiceInterface {

    private final CommentDao commentDao;

    @Autowired
    public CommentService(CommentDao commentDao) {
        this.commentDao = commentDao;
    }

    // ----------------------------------------------- saveComment -------------------------------------------------- //

    /*public void addComment(Comment comment) {
        commentDao.save(comment);
    }*/

    public void addComment(CommentDTO commentDTO) {
        Comment comment = commentDTO.convertToModel(commentDTO);
        commentDao.save(comment);
    }

    // ----------------------------------------------- saveComment -------------------------------------------------- //

    public List<Comment> getComments() {
        return commentDao.findAll();
    }

    // ----------------------------------------------- updateComment -------------------------------------------------- //

    /*public Comment updateComment(long id, Comment comment) {
        Comment commentToUpdate = commentDao.findById(id).get();
        commentToUpdate.setText(comment.getText());
        return commentDao.save(commentToUpdate);
    }*/

    public Comment updateComment(long id, CommentDTO commentDTO) {
        Comment commentToUpdate = commentDao.findById(id).get();
        commentToUpdate.setText(commentDTO.getText());
        return commentDao.save(commentToUpdate);
    }

    // ----------------------------------------------- updateComment -------------------------------------------------- //

    public void deleteComment(long id) {
        Comment comment = commentDao.findById(id).get();
        commentDao.delete(comment);
    }
}
