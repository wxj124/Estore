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
		// 1.��ȡ������
		String activecode = req.getParameter("activecode");
		// 2.����service�з��������û�
		User user = service.active(activecode);
		// 3.��¼�û�
		req.getSession().setAttribute("user", user);
		// 4.�ض������ҳ
		resp.getWriter().write("���伤��ɹ���3���ص���ҳ");
		resp.setHeader("Refresh", "3;url=./index.jsp");
	}

}
