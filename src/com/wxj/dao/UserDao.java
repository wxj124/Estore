package com.wxj.dao;

import java.sql.Connection;
import java.util.Hashtable;
import java.util.List;

import com.wxj.domian.User;

public interface UserDao {
	/**
	 * �����û��������û�
	 * @param conn 
	 * @return
	 */
	User findUserByName(String username, Connection conn);
	/**
	 * ����û������ݿ�
	 * @param user ��װ���û���Ϣ��bean
	 */
	void addUser(User user, Connection conn);
	/**
	 * �������������û���Ϣ
	 * @param findU
	 * @return
	 */
	List<User> find(User findU);
	/**
	 * �����û� id ɾ���û�
	 * @param id
	 */
	void delete(int id);
	/**
	 * �����û�id�޸�״̬��
	 * @param id
	 */
	void updateState(int id);
	/**
	 * �����û�����������û�
	 * @param username
	 * @param password
	 * @return
	 */
	User findUserByNameAndPsw(String username, String password);
	
	
	
	
}
