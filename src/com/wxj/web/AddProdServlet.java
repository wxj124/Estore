package com.wxj.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.wxj.domian.Product;
import com.wxj.factory.BasicFactory;
import com.wxj.service.ProdService;
import com.wxj.util.IOUtils;
import com.wxj.util.PicUtils;

public class AddProdServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddProdServlet() {
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
		try {
			ProdService service = BasicFactory.getFactory().getInstence(
					ProdService.class);
			String encode = this.getServletContext().getInitParameter("encode");
			Map<String, String> paramMap = new HashMap<String, String>();
			// 1.上传图片
			DiskFileItemFactory factory = new DiskFileItemFactory();
			factory.setSizeThreshold(1024 * 100);
			factory.setRepository(new File(this.getServletContext()
					.getRealPath("WEB-INF/temp"))); // 临时文件夹的位置

			ServletFileUpload fileUpload = new ServletFileUpload(factory);
			fileUpload.setHeaderEncoding(encode); // 解决文件名乱码问题
			fileUpload.setFileSizeMax(1024 * 1024 * 1);
			fileUpload.setSizeMax(1024 * 1024 * 10);

			if (!fileUpload.isMultipartContent(req)) { // 如果当前的表单不是正确的表单，做出提示
				throw new RuntimeException("请使用正确的表单进行上传!");
			}
			List<FileItem> list = fileUpload.parseRequest(req);
			for (FileItem item : list) {
				if (item.isFormField()) {
					// 普通字段
					String name = item.getFieldName();
					String value = item.getString(encode);
					paramMap.put(name, value);
				} else {
					// 文件上传项
					String realname = item.getName();
					String uuidname = UUID.randomUUID().toString() + "_"
							+ realname;

					String hash = Integer.toHexString(uuidname.hashCode());
					String upload = this.getServletContext().getRealPath(
							"WEB-INF/upload");
					String imgurl = "./WEB-INF/upload";
					for (char c : hash.toCharArray()) {
						upload += "/" + c;
						imgurl += "/" + c;
					}
					imgurl += "/" + uuidname;
					paramMap.put("imgurl", imgurl);

					File uploadFile = new File(upload);
					if (!uploadFile.exists())
						uploadFile.mkdirs();

					InputStream in = item.getInputStream();
					OutputStream out = new FileOutputStream(new File(upload,
							uuidname));

					IOUtils.In2Out(in, out);
					IOUtils.close(in, out);

					item.delete();

					 //生成缩略图
					 PicUtils picu = new
					 PicUtils(this.getServletContext().getRealPath(imgurl));
					 picu.resizeByHeight(140);
				}
			}

			// 2.调用Service中提供的方法,在数据库中添加商品
			Product prod = new Product();
			BeanUtils.populate(prod, paramMap);
			service.addProd(prod);

			// 3.提示成功,回到主页
			resp.getWriter().write("添加商品成功!3秒回到主页..");
			resp.setHeader("Refresh", "3;url=./index.jsp");

		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
