package com.example.Live.Auctions;

import com.example.Live.Auctions.dao.CommentDao;
import com.example.Live.Auctions.dto.CommentDTO;
import com.example.Live.Auctions.model.Comment;
import com.example.Live.Auctions.service.impl.CommentService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DataJpaTest
public class CommentTest {

    @InjectMocks
    CommentService commentService;

    @Mock
    CommentDao commentDao;

    @Test
    public void getCommentsTest() {

        List<Comment> commentList = new ArrayList<>();

        commentList.add(new Comment("Expensive product"));
        commentList.add(new Comment("So useful. Love it"));

        when(commentDao.findAll()).thenReturn(commentList);

        List<Comment> result = commentService.getComments();

        assertEquals(2, result.size());
        assertEquals("Expensive product", result.get(0).getText());

        assertEquals("So useful. Love it", result.get(1).getText());

    }

    @Test
    public void createCommentTest() {
        Comment comment = new Comment("Too expensive");

        when(commentDao.save(any(Comment.class))).thenReturn(new Comment());

        commentService.addComment(comment.convertToDTO(comment));

        ArgumentCaptor<Comment> captor = ArgumentCaptor.forClass(Comment.class);
        verify(commentDao, times(1)).save(captor.capture());
        Comment saveComment = captor.getValue();

        assertEquals("Too expensive", saveComment.getText());
    }

    @Test
    public void updateCommentTest() {
        long id = 0L;
        Comment comment = new Comment("Too expensive");

        Comment existingComment = new Comment("Expensive product");

        when(commentDao.findById(id)).thenReturn(Optional.of(existingComment));
        when(commentDao.save(existingComment)).thenReturn(existingComment);

        Comment updateComment = commentService.updateComment(id, comment.convertToDTO(comment));

        assertEquals(comment.getText(), updateComment.getText());
    }
}
