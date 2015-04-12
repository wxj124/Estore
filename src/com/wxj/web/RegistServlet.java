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
			// 1.校验验证码
			String valistr1 = req.getParameter("valistr");
			String valistr2 = (String) req.getSession().getAttribute("valistr");
			if (valistr1 == null || valistr2 == null
					|| !valistr1.equals(valistr2)) {
				req.setAttribute("msg", "验证码不正确");
				req.getRequestDispatcher("/regist.jsp").forward(req, resp);
				return;
			}
			// 2.封装数据,校验数据
			User user = new User();
			BeanUtils.populate(user, req.getParameterMap());
			user.setPassword(MD5Utils.md5(user.getPassword()));// 应用md5加密，修改密码明文的问题
			// 3.调用service中的方法，添加用户
			service.regist(user);
			// 4.重定向回主页
			resp.getWriter().write("请到邮箱中激活，3秒后自动回到主页...");
			resp.setHeader("Refresh", "3;url=./index.jsp");
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
