package com.example.Live.Auctions.api;

import com.example.Live.Auctions.dto.CommentDTO;
import com.example.Live.Auctions.model.Comment;
import com.example.Live.Auctions.service.impl.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentApiController {

    private final CommentService commentService;

    @Autowired
    public CommentApiController(CommentService commentService) {
        this.commentService = commentService;
    }

    // ----------------------------------------------- saveComment -------------------------------------------------- //

    /*@Operation(summary = "Save comment", description = "Save a new comment in the database")
    @PostMapping(value = "/saveComment")
    public String saveComment(@RequestBody Comment comment) {
        commentService.addComment(comment);
        return "Saved...";
    }*/

    // "saveComment" changed for DTO
    @Operation(summary = "Save comment", description = "Save a new comment in the database")
    @PostMapping(value = "/saveComment")
    public String saveComment(@RequestBody CommentDTO commentDTO) {
        commentService.addComment(commentDTO);
        return "Saved...";
    }

    // ----------------------------------------------- saveComment -------------------------------------------------- //

    @Operation(summary = "Get comments ", description = "Get all the comments from DB")
    @GetMapping(value = "/comments")
    public List<Comment> getComments() {
        return commentService.getComments();
    }

    // ----------------------------------------------- updateComment -------------------------------------------------- //

    /*@Operation(summary = "Modify comment", description = "Modify comment by id")
    @PostMapping(value = "/updateComment/{id}")
    public String updateComment(@PathVariable long id, @RequestBody Comment comment) {
        commentService.updateComment(id, comment);
        return "Comment updated...";
    }*/

    @Operation(summary = "Modify comment", description = "Modify comment by id")
    @PostMapping(value = "/updateComment/{id}")
    public String updateComment(@PathVariable long id, @RequestBody CommentDTO commentDTO) {
        commentService.updateComment(id, commentDTO);
        return "Comment updated...";
    }

    // ----------------------------------------------- updateComment -------------------------------------------------- //

    @Operation(summary = "Delete comment", description = "Delete comment by id")
    @DeleteMapping(value = "/deleteComment/{id}")
    public String deleteComment(@PathVariable long id) {
        commentService.deleteComment(id);
        return "Delete comment with id " + id;
    }
}
