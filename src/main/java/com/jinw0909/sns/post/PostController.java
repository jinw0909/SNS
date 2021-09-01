package com.jinw0909.sns.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jinw0909.sns.post.bo.PostBO;
import com.jinw0909.sns.post.model.PostWithComments;

@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostBO postBO;
		
	@GetMapping("/create_view")
	public String createView(Model model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		int userId = (Integer)session.getAttribute("userId");
		
		List<PostWithComments> postList = postBO.getPostList(userId);
		model.addAttribute("postList", postList);
		return "post/createView";
	}
	
	
}
