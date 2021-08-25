package com.jinw0909.sns.post.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jinw0909.sns.common.FileManagerService;
import com.jinw0909.sns.post.dao.PostDAO;

@Service
public class PostBO {
	
	@Autowired
	private PostDAO postDAO;
	
	public int addPost(int userId, String userName, String content, MultipartFile file) {
		
		//file to url path logic comes here
		FileManagerService fileManager = new FileManagerService();
		String filePath = fileManager.saveFile(userName, file);
		
		if(filePath == null) {
			return -1;
		}
		
		return postDAO.insertPost(userId, userName, content, filePath);
	}
}
