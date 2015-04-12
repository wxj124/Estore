package com.wxj.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.wxj.domian.User;
import com.wxj.factory.BasicFactory;
import com.wxj.service.UserService;
import com.wxj.util.MD5Utils;

public class RegistServlet extends HttpServlet {
	/**
	 * Constructor of the object.
	 */
	public RegistServlet() {
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
		try {
			// 1.У����֤��
			String valistr1 = req.getParameter("valistr");
			String valistr2 = (String) req.getSession().getAttribute("valistr");
			if (valistr1 == null || valistr2 == null
					|| !valistr1.equals(valistr2)) {
				req.setAttribute("msg", "��֤�벻��ȷ");
				req.getRequestDispatcher("/regist.jsp").forward(req, resp);
				return;
			}
			// 2.��װ����,У������
			User user = new User();
			BeanUtils.populate(user, req.getParameterMap());
			user.setPassword(MD5Utils.md5(user.getPassword()));// Ӧ��md5���ܣ��޸��������ĵ�����
			// 3.����service�еķ���������û�
			service.regist(user);
			// 4.�ض������ҳ
			resp.getWriter().write("�뵽�����м��3����Զ��ص���ҳ...");
			resp.setHeader("Refresh", "3;url=./index.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
