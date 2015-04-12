package com.wxj.service;

import java.util.List;

import com.wxj.domian.Product;

public interface ProdService {
	/**
	 * ����û�
	 * @param prod ��Ʒ��Ϣ��bean
	 */
	void addProd(Product prod);
	/**
	 * ��ѯ���е���Ʒ��Ϣ
	 * @return
	 */
	List<Product> findAllProd();
	/**
	 * ����id������Ʒ
	 * @param id
	 * @return
	 */
	Product findProdById(String id);

}
