package com.wxj.service;

import com.wxj.domian.User;

public interface UserService {
	/**
	 * ע���û�
	 * @param user
	 */
	void regist(User user);
	/**
	 * �����û�
	 * @param activecode
	 * @return
	 */
	User active(String activecode);
	/**
	 * �����û�����������û�
	 * @param username
	 * @param password
	 * @return 
	 */
	User getUserByNameAndPsw(String username, String password);

}
