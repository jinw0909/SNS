package com.jinw0909.sns.post.comment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jinw0909.sns.post.comment.model.Comment;

@Repository
public interface CommentDAO {
	public int insertComment(@Param("content") String content
				, @Param("postId") int postId
				, @Param("userId") int userId
				,@Param("userName") String userName
			);

	public List<Comment> selectCommentByPostId(
			@Param("postId") int postId);
	
	public int deleteCommentByPostId(@Param("postId") int postId);
}
