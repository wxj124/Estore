package com.wxj.filter;

import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wxj.domian.User;
import com.wxj.factory.BasicFactory;
import com.wxj.service.UserService;

public class AutoLoginFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		// 1.ֻ��δ��¼���û����Զ���¼
		if (req.getSession(false) == null
				|| req.getSession().getAttribute("user") == null) {
			// 2.ֻ�д����Զ���½cookie���û����Զ���½
			Cookie[] cs = req.getCookies();
			Cookie findC = null;
			if (cs != null) {
				for (Cookie c : cs) {
					if ("autologin".equals(c.getName())) {
						findC = c;
						break;
					}
				}
			}
			if (findC != null) {
				// 3.ֻ���Զ���¼cookie�е��û������붼��ȷ�����Զ���½
				String v = URLDecoder.decode(findC.getValue(), "utf-8");
				String username = v.split(":")[0];
				String password = v.split(":")[1];
				UserService service = BasicFactory.getFactory().getInstence(
						UserService.class);
				User user = service.getUserByNameAndPsw(username, password);
				if (user != null) {
					req.getSession().setAttribute("user", user);
				}
			}
		}
		// 4.�����Ƿ��Զ���½��Ҫ����
		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

}
