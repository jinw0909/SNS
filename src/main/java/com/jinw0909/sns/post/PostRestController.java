package com.jinw0909.sns.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jinw0909.sns.post.bo.LikeBO;
import com.jinw0909.sns.post.bo.PostBO;

@RestController
@RequestMapping("/post")
public class PostRestController {
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private LikeBO likeBO;
	
	@PostMapping("/create")
	public Map<String, String> create(
			@RequestParam("content") String content
			, @RequestParam(value = "file", required = false) MultipartFile file
			, HttpServletRequest request
			) {
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("userId");
		String userName = (String)session.getAttribute("userName");
		
		int count = postBO.addPost(userId, userName, content, file);
		
		Map<String, String> result = new HashMap<>();
		
		if (count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "failure");
		}
		
		return result;
	}
	
	@GetMapping("/like")
	public Map<String, String> like(
			@RequestParam("postId") int postId
			, HttpServletRequest request
			) {
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("userId");
		int count = likeBO.like(postId, userId);
		Map<String, String> result = new HashMap<>();
		
		if (count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "failure");
		}
		
		return result;
		
	}
	
	@PostMapping("/delete_like")
	public Map<String, String> deleteLike(
			@RequestParam("postId") int postId
			, HttpServletRequest request
			) {
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("userId");
		int count = likeBO.deleteLike(postId, userId);
		
		Map<String, String> result = new HashMap<>();
		
		if (count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "failure");
		}
		
		return result;
	}
	
	@PostMapping("/delete")
	public Map<String, String> delete(
			@RequestParam("postId") int postId
			, HttpServletRequest request) {
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("userId");
		int count = postBO.deletePost(postId, userId);
		
		Map<String, String> result = new HashMap<>();
		
		if (count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "failure");
		}
		
		return result;
	}
	
	
}
