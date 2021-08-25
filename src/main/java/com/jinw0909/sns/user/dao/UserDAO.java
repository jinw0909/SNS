package com.jinw0909.sns.user.dao;

import org.apache.ibatis.annotations.Param;

import com.jinw0909.sns.user.model.User;

public interface UserDAO {
	public int insertUser(
			@Param("loginId") String loginId
			, @Param("password") String password
			, @Param("name") String name
			, @Param("email") String email);
	
	public User selectUserByIdPassword(
			@Param("loginId") String loginId
			, @Param("password") String password
			);
	
	public int selectCountById(@Param("loginId") String loginId);
	
}
