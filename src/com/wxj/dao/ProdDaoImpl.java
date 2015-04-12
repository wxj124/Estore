package com.wxj.dao;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.wxj.domian.Product;
import com.wxj.util.DaoUtils;

public class ProdDaoImpl implements ProdDao {

	@Override
	public void addProd(Product prod) {
		String sql = "insert into t_products values(null,?,?,?,?,?,?)";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			runner.update(sql, prod.getName(), prod.getPrice(),
					prod.getCategory(), prod.getPnum(), prod.getImgurl(),
					prod.getDescription());
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}

	}

	@Override
	public List<Product> findAllProd() {
		String sql = "select * from t_products";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql,
					new BeanListHandler<Product>(Product.class));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Override
	public Product findProdById(String id) {
		String sql = "select * from t_products where id=?";
		try {
			QueryRunner runner = new QueryRunner(DaoUtils.getSource());
			return runner.query(sql, new BeanHandler<Product>(Product.class),
					id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
