package com.wxj.web;

import java.io.IOException;
import java.util.List;

import javax.persistence.Basic;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wxj.domian.Product;
import com.wxj.factory.BasicFactory;
import com.wxj.service.ProdService;

public class ListProdServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ListProdServlet() {
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
		ProdService service = BasicFactory.getFactory().getInstence(ProdService.class);
		//1.����service��������ѯ������Ʒ��Ϣ
		List<Product> list = service.findAllProd();
		//2.����Ϣ����req���У�����ҳ��չʾ
		req.setAttribute("list", list);
		req.getRequestDispatcher("/listProd.jsp").forward(req, resp);
	}

}
