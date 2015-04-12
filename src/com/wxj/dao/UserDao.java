package com.wxj.dao;

import java.sql.Connection;
import java.util.Hashtable;
import java.util.List;

import com.wxj.domian.User;

public interface UserDao {
	/**
	 * 根据用户名查找用户
	 * @param conn 
	 * @return
	 */
	User findUserByName(String username, Connection conn);
	/**
	 * 添加用户到数据库
	 * @param user 封装了用户信息的bean
	 */
	void addUser(User user, Connection conn);
	/**
	 * 根据条件查找用户信息
	 * @param findU
	 * @return
	 */
	List<User> find(User findU);
	/**
	 * 根据用户 id 删除用户
	 * @param id
	 */
	void delete(int id);
	/**
	 * 根据用户id修改状态码
	 * @param id
	 */
	void updateState(int id);
	/**
	 * 根据用户名密码查找用户
	 * @param username
	 * @param password
	 * @return
	 */
	User findUserByNameAndPsw(String username, String password);
	
	
	
	
}
