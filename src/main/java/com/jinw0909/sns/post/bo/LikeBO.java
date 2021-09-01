package com.jinw0909.sns.post.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jinw0909.sns.post.dao.LikeDAO;

@Service
public class LikeBO {

	@Autowired
	private LikeDAO likeDAO;
	
	public int like(int postId, int userId) {
		return likeDAO.insertLike(postId, userId);
	}
	
	public int existLike(int postId, int userId) {
		return likeDAO.selectCountLike(postId, userId);
	}
}
