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
	
	public void removeFile(String filePath) {
		
		// 삭제할 파일 경로
		// filePath : /images/10_21924214/test.png
		// 실제 파일이 저장된 경로 : D:\\김인규강사\\web\\0415\\spring_test\\upload\\marondalgram\\images\\10_21924214\\test.png
		
		
		String realFilePath = FILE_UPLOAD_PATH + filePath.replace("/images/", "");
		
		// 파일지우고
		Path path = Paths.get(realFilePath);
		// 해당 파일이 있는지
		if(Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				logger.error("[FileManagerService saveFile] file delete fail ");
				e.printStackTrace();
			}
		}
		
		// 디렉토리(폴더) 지우고
		//D:\\김인규강사\\web\\0415\\spring_test\\upload\\marondalgram\\images\\10_21924214
		path = path.getParent();
		
		if(Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				logger.error("[FileManagerService saveFile] directory delete fail ");
				e.printStackTrace();
			}
		}
	}
	
//	public void removeFile(String filePath) {
//		
//		// 삭제할 파일 경로
//		// filePath: /images/10_2192148/test.png
//		// 실제 파일이 저장된 경로: D://user/
//		
//	}
	
	
	
}
