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
		// 1.��ȡ�û�������
		String username = req.getParameter("username");
		String password = MD5Utils.md5(req.getParameter("password"));
		// 2.��service���������û�
		User user = service.getUserByNameAndPsw(username, password);
		if (user == null) {
			req.setAttribute("msg", "�û������벻��ȷ");
			req.getRequestDispatcher("./login.jsp").forward(req, resp);
			return;
		}
		// 3.��鼤����
		if (user.getState() == 0) {
			req.setAttribute("msg", "�û�δע�ἤ��뵽�����м���");
			req.getRequestDispatcher("./login.jsp").forward(req, resp);
			return;
		}
		// 4.��¼�û����ض������ҳ
		req.getSession().setAttribute("user", user);
		if ("true".equals(req.getParameter("remname"))) {
			// �����ס�û���,urlencoder,���û�һ������
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

		// 30 ���Զ���¼
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
