package com.wxj.service;

import java.util.List;
import java.util.UUID;

import com.wxj.dao.ProdDao;
import com.wxj.domian.Product;
import com.wxj.factory.BasicFactory;

public class ProdServiceImpl implements ProdService {
	ProdDao dao = BasicFactory.getFactory().getInstence(ProdDao.class);

	@Override
	public void addProd(Product prod) {
		// prod.setId(UUID.randomUUID().toString());
		dao.addProd(prod);
	}

	@Override
	public List<Product> findAllProd() {
		return dao.findAllProd();
	}

	@Override
	public Product findProdById(String id) {
		return dao.findProdById(id);
	}

}
