package com.blogging.service.service;

import com.blogging.service.dao.CommentRepository;
import com.blogging.service.dao.PostRepo;
import com.blogging.service.dao.UserRepo;
import com.blogging.service.enitities.Comment;
import com.blogging.service.enitities.Post;
import com.blogging.service.enitities.User;
import com.blogging.service.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private PostRepo postRepository;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public Comment getCommentById(Long id) {
        return commentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment", "id", id));
    }

    public List<Comment> getCommentsByPostId(Long postId) {
        return commentRepository.findByPostId(postId);
    }

    public Comment createComment(Comment comment) {
        Optional<User> userOptional = userRepository.findById(comment.getUser().getId());
        Optional<Post> postOptional = postRepository.findById(comment.getPost().getId());
        if (userOptional.isPresent() && postOptional.isPresent()) {
            comment.setUser(userOptional.get());
            comment.setPost(postOptional.get());
            return commentRepository.save(comment);
        } else {
            throw new ResourceNotFoundException("User or Post", "id", comment.getUser().getId() + " or " + comment.getPost().getId());
        }
    }

    public Comment updateComment(Long id, Comment comment) {
        Comment existingComment = getCommentById(id);
        existingComment.setContent(comment.getContent());
        Optional<User> userOptional = userRepository.findById(comment.getUser().getId());
        Optional<Post> postOptional = postRepository.findById(comment.getPost().getId());
        if (userOptional.isPresent() && postOptional.isPresent()) {
            existingComment.setUser(userOptional.get());
            existingComment.setPost(postOptional.get());
            return commentRepository.save(existingComment);
        } else {
            throw new ResourceNotFoundException("User or Post", "id", comment.getUser().getId() + " or " + comment.getPost().getId());
        }
    }

    public void deleteComment(Long id) {
        Comment comment = getCommentById(id);
        commentRepository.delete(comment);
    }
}
