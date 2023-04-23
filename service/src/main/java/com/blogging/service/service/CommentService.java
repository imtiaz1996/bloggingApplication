package com.blogging.service.service;

import com.blogging.service.enitities.Comment;
import java.util.List;

public interface CommentService {
    List<Comment> getAllComments();

    Comment getCommentById(Long id);

    List<Comment> getCommentsByPostId(Long postId);

    Comment createComment(Comment comment);

    Comment updateComment(Long id, Comment comment);

    void deleteComment(Long id);
}
