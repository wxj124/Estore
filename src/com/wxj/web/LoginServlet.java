package com.wxj.web;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wxj.domian.User;
import com.wxj.factory.BasicFactory;
import com.wxj.service.UserService;
import com.wxj.util.MD5Utils;

public class LoginServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoginServlet() {
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
		// 1.获取用户名密码
		String username = req.getParameter("username");
		String password = MD5Utils.md5(req.getParameter("password"));
		// 2.调service方法查找用户
		User user = service.getUserByNameAndPsw(username, password);
		if (user == null) {
			req.setAttribute("msg", "用户名密码不正确");
			req.getRequestDispatcher("./login.jsp").forward(req, resp);
			return;
		}
		// 3.检查激活码
		if (user.getState() == 0) {
			req.setAttribute("msg", "用户未注册激活，请到邮箱中激活");
			req.getRequestDispatcher("./login.jsp").forward(req, resp);
			return;
		}
		// 4.登录用户，重定向回主页
		req.getSession().setAttribute("user", user);
		if ("true".equals(req.getParameter("remname"))) {
			// 处理记住用户名,urlencoder,给用户一个编码
			Cookie remnameC = new Cookie("remname", URLEncoder.encode(
					user.getUsername(), "utf-8"));
			remnameC.setPath("/");
			remnameC.setMaxAge(3600 * 24 * 30);
			resp.addCookie(remnameC);
		} else {
			Cookie remnameC = new Cookie("remname", "");
			remnameC.setPath("/");
			remnameC.setMaxAge(0);
			resp.addCookie(remnameC);
		}

		// 30 天自动登录
		if ("true".equals(req.getParameter("autologin"))) {
			Cookie autologinC = new Cookie("autologin", URLEncoder.encode(
					user.getUsername() + user.getPassword(), "utf-8"));
			autologinC.setPath("/");
			autologinC.setMaxAge(3600 * 24 * 30);
			resp.addCookie(autologinC);
		}
		resp.sendRedirect("./index.jsp");
	}

}
