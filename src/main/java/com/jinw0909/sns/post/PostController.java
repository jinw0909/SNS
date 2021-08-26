package com.jinw0909.sns.post;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jinw0909.sns.post.dao.PostDAO;
import com.jinw0909.sns.post.model.Post;

@Controller
@RequestMapping("/post")
public class PostController {
	
	@Autowired
	private PostDAO postDAO;
		
	@GetMapping("/create_view")
	public String createView(Model model,
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		
		List<Post> postList = postDAO.selectPostList();
		model.addAttribute("postList", postList);
		return "post/createView";
	}
	
	
}
