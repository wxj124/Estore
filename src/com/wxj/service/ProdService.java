package com.wxj.service;

import java.util.List;

import com.wxj.domian.Product;

public interface ProdService {
	/**
	 * 添加用户
	 * @param prod 商品信息的bean
	 */
	void addProd(Product prod);
	/**
	 * 查询所有的商品信息
	 * @return
	 */
	List<Product> findAllProd();
	/**
	 * 根据id查找商品
	 * @param id
	 * @return
	 */
	Product findProdById(String id);

}
