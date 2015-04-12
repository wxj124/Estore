package com.wxj.dao;

import java.util.List;

import com.wxj.domian.Product;

public interface ProdDao {
	/**
	 * �����Ʒ��Ϣ
	 * 
	 * @param prod
	 */
	void addProd(Product prod);

	/**
	 * ��ѯ������Ʒ��Ϣ
	 * 
	 * @return
	 */
	List<Product> findAllProd();

	/**
	 * ����id������Ʒ��Ϣ
	 * 
	 * @param id
	 * @return
	 */
	Product findProdById(String id);

}
