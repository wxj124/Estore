package com.wxj.service;

import com.wxj.domian.User;

public interface UserService {
	/**
	 * 注册用户
	 * @param user
	 */
	void regist(User user);
	/**
	 * 激活用户
	 * @param activecode
	 * @return
	 */
	User active(String activecode);
	/**
	 * 根据用户名密码查找用户
	 * @param username
	 * @param password
	 * @return 
	 */
	User getUserByNameAndPsw(String username, String password);

}
