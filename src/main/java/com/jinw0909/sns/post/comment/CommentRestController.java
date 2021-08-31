package com.jinw0909.sns.post.comment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jinw0909.sns.post.comment.bo.CommentBO;

@RestController
@RequestMapping("/post")
public class CommentRestController {
	@Autowired 
	private CommentBO commentBO;
	
	@PostMapping("/comment/create")
	public Map<String, String> create(@RequestParam("postId") int postId
			, @RequestParam("content") String content
			, HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		int userId = (Integer)session.getAttribute("userId");
		String userName = (String)session.getAttribute("userName");
		
		Map<String, String> result = new HashMap<>();
		
		int count = commentBO.addComment(content, postId, userId, userName);
		
		if(count == 1) {
			result.put("result", "success");
		} else {
			result.put("result", "fail");
		}
		
		return result;
		
	}
	
}
