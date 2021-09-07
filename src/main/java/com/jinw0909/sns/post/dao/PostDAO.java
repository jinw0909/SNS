package com.jinw0909.sns.post.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.jinw0909.sns.post.model.Post;

@Repository
public interface PostDAO {
	public int insertPost(
			@Param("userId") int userId
			, @Param("userName") String userName
			, @Param("content") String content
			, @Param("imagePath") String imagePath
			);
	
	public List<Post> selectPostList();
	
	public int deletePost(@Param("postId") int postId, @Param("userId") int userId);
	
	public Post selectPost(@Param("id") int id);
}
