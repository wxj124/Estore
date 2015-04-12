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
//		// 根据id查找到商品信息
//		Product prod = service.findProdById(req.getParameter("id"));
//		// 获取图片的URL，输出图片
//		String imgurl = prod.getImgurl();
		String imgurl =req.getParameter("imgurl"); 
		req.getRequestDispatcher(imgurl).forward(req, resp);
	}

}
