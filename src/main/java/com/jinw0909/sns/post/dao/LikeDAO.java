package com.jinw0909.sns.post.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeDAO {
	public int insertLike(
			@Param("postId") int postId
			, @Param("userId") int userId
			);
	
	public int selectCountLike(
			@Param("postId") int postId
			, @Param("userId") int userId
			);
	
	public int deleteLike(
			@Param("postId") int postId
			, @Param("userId") int userId
			);
	
	public int countLike(
			@Param("postId") int postId
			);
	public int deleteLikeByPostId(@Param("postId") int postId);
}
