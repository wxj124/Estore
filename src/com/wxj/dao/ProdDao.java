package com.wxj.dao;

import java.util.List;

import com.wxj.domian.Product;

public interface ProdDao {
	/**
	 * 添加商品信息
	 * 
	 * @param prod
	 */
	void addProd(Product prod);

	/**
	 * 查询所有商品信息
	 * 
	 * @return
	 */
	List<Product> findAllProd();

	/**
	 * 根据id查找商品信息
	 * 
	 * @param id
	 * @return
	 */
	Product findProdById(String id);

}
