package com.wxj.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wxj.domian.User;
import com.wxj.factory.BasicFactory;
import com.wxj.service.UserService;

public class ActiveServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ActiveServlet() {
		super();
	}

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		UserService service = BasicFactory.getFactory().getInstence(
				UserService.class);
		// 1.获取激活码
		String activecode = req.getParameter("activecode");
		// 2.调用service中方法激活用户
		User user = service.active(activecode);
		// 3.登录用户
		req.getSession().setAttribute("user", user);
		// 4.重定向回主页
		resp.getWriter().write("邮箱激活成功，3秒后回到主页");
		resp.setHeader("Refresh", "3;url=./index.jsp");
	}

}
