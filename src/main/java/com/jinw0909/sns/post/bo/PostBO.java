package com.jinw0909.sns.post.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jinw0909.sns.common.FileManagerService;
import com.jinw0909.sns.post.comment.bo.CommentBO;
import com.jinw0909.sns.post.comment.model.Comment;
import com.jinw0909.sns.post.dao.PostDAO;
import com.jinw0909.sns.post.model.Post;
import com.jinw0909.sns.post.model.PostWithComments;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	public int addPost(int userId, String userName, String content, MultipartFile file) {
		
		//file to url path logic comes here
		FileManagerService fileManager = new FileManagerService();
		String filePath = fileManager.saveFile(userName, file);
		
		if(filePath == null) {
			return -1;
		}
		
		return postDAO.insertPost(userId, userName, content, filePath);
	}
	
	public List<PostWithComments> getPostList(int userId) {
		
		List<Post> postList = postDAO.selectPostList();
		List<PostWithComments> postWithCommentsList = new ArrayList<>();
		boolean isLike = false;
		
		
		for (Post post: postList) {
			List<Comment> commentList = commentBO.getCommentListByPostId(post.getId());
			if (likeBO.existLike(post.getId(), userId) >= 1) {
				isLike = true;
			} else {
				isLike = false;
			}
			int likeCount = likeBO.countLike(post.getId());
			PostWithComments postWithComments = new PostWithComments();
			postWithComments.setPost(post);
			postWithComments.setCommentList(commentList);
			postWithComments.setLike(isLike);
			postWithComments.setLikeCount(likeCount);
			
			postWithCommentsList.add(postWithComments);
		}
		
		return postWithCommentsList;
	} 
	
	public int deletePost(int postId, int userId) {
		
		likeBO.deleteLikeByPostId(postId);
		commentBO.deleteCommentByPostId(postId);
		
		Post post = postDAO.selectPost(postId);
		FileManagerService fileManagerService = new FileManagerService();
		fileManagerService.removeFile(post.getImagePath());
		
		return postDAO.deletePost(postId, userId);
	}
}
