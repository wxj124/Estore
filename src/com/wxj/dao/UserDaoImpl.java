package com.wxj.dao;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.wxj.domian.User;
import com.wxj.util.DaoUtils;

public class UserDaoImpl implements UserDao {
	/**
	 *  µœ÷¿‡
	 */
	@Override
	public void addUser(User user,Connection conn) {
		try {
			String sql = "insert into t_users values(null,?,?,?,?,?,?,?,null)";
			QueryRunner runner = new QueryRunner();
			runner.update(conn,sql,user.getUsername(),user.getPassword(),user.getEmail(),user.getState(),user.getNickname(),user.getRole(),user.getActivecode());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public User findUserByName(String username,Connection conn) {
		try {
			String sql = "select * from t_users where username=?";
			QueryRunner runner = new QueryRunner();
			return runner.query(conn,sql,new BeanHandler<User>(User.class),username);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	
	public List<User> find(User findU) {
		// TODO Auto-generated method stub
		String sql = "select * from t_users where 1=1";
		List<String> list = new ArrayList<String>();
		if(findU.getId()!=0){
			sql += " and id = ? ";
			list.add(findU.getId()+"");
		}
		if (findU.getUsername() != null && !"".equals(findU.getUsername())) {
			sql += " and username = ? ";
			list.add(findU.getUsername());
		}
		if (findU.getPassword() != null && !"".equals(findU.getPassword())) {
			sql += " and password=? "; 
			list.add(findU.getPassword());
		}
		if (findU.getEmail() != null && !"".equals(findU.getEmail())) {
			sql += " and email = ? ";
			list.add(findU.getEmail());
		}
		if (findU.getNickname() != null && !"".equals(findU.getNickname())) {
			sql += " and nickname = ? ";
			list.add(findU.getNickname());
		}
		if (findU.getState() != 0) {
			sql += " and state = ? ";
			list.add(findU.getState()+"");
		}
		if (findU.getActivecode() != null && !"".equals(findU.getActivecode())) {
			sql += " and activecode = ? ";
			list.add(findU.getActivecode());
		}
		if (findU.getRole() != null && !"".equals(findU.getRole())) {
			sql += " and role = ? ";
			list.add(findU.getRole());
		}
		
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			if (list.size() <= 0) {
				return runner.query(sql, new BeanListHandler<User>(User.class));
			} else {
				return runner.query(sql, new BeanListHandler<User>(User.class),
						list.toArray());
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void delete(int id) {
		try {
			String sql = "delete from t_users where id = ?";
			QueryRunner runner = new QueryRunner();
			runner.update(sql,id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public void updateState(int id) {
		try {
			String sql = "update t_users set state = 1 where id = ?";
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql,id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public User findUserByNameAndPsw(String username, String password) {
		String sql = "select * from t_users where username=? and password=?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql,new BeanHandler<User>(User.class),username,password);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
