package com.wxj.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wxj.domian.Product;
import com.wxj.factory.BasicFactory;
import com.wxj.service.ProdService;

public class ImgServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ImgServlet() {
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
//		ProdService service = BasicFactory.getFactory().getInstence(
//				ProdService.class);
//		// ����id���ҵ���Ʒ��Ϣ
//		Product prod = service.findProdById(req.getParameter("id"));
//		// ��ȡͼƬ��URL�����ͼƬ
//		String imgurl = prod.getImgurl();
		String imgurl =req.getParameter("imgurl"); 
		req.getRequestDispatcher(imgurl).forward(req, resp);
	}

}
