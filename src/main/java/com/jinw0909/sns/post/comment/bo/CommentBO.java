package com.jinw0909.sns.post.comment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinw0909.sns.post.comment.dao.CommentDAO;
import com.jinw0909.sns.post.comment.model.Comment;

@Service
public class CommentBO {
	
	@Autowired
	private CommentDAO commentDAO;
	
	public int addComment(String content, int postId, int userId, String userName) {
		return commentDAO.insertComment(content, postId, userId, userName);
	}
	
	public List<Comment> getCommentListByPostId(int postId) {
		return commentDAO.selectCommentByPostId(postId);
	}
}
