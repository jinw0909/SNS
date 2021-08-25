package com.jinw0909.sns.common;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileManagerService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private final String FILE_UPLOAD_PATH = "/Users/jinwoo/jinwoo/workspace_javaSpringTest/upload/sns/images/";
	
	public String saveFile(String userName, MultipartFile file) {
		
		String directoryName = userName + "_" + System.currentTimeMillis() + "/";
		
		String filePath = FILE_UPLOAD_PATH + directoryName;
		
		File directory = new File(filePath);
		
		if (directory.mkdir() == false) {
			logger.error("[FileManagerService saveFile 디렉토리 생성 실패]");
			return null;
		}
		
		try {
			byte[] bytes;
			bytes = file.getBytes();
			Path path = Paths.get(filePath + file.getOriginalFilename());
			Files.write(path, bytes);
		} catch (IOException e) {
			logger.error("[FileManagerService saveFile] 파일 생성 실패 ");
			e.printStackTrace();
			return null;
		}
		
		return "/images/" + directoryName + file.getOriginalFilename();
	}
	
	
	
}
