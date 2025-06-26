package com.igeek.shop.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.igeek.shop.dao.ProductDao;
import com.igeek.shop.dao.mysql.ProductDaoImpl;
import com.igeek.shop.entity.Product;
import com.igeek.shop.service.ProductService;
import com.igeek.shop.utils.C3P0Utils;
import com.igeek.shop.utils.PageUtils;

public class ProductServiceImpl implements ProductService {

	private ProductDao productDao = new ProductDaoImpl();

	@Override
	public List<Product> searchHotProducts(int count) {
		List<Product> products = new ArrayList<>();

		Product condition = new Product();
		condition.setIs_hot(1);

		try {
			products = productDao.selectByCondition(condition, 0, count);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			C3P0Utils.close();
		}

		return products;
	}

	@Override
	public List<Product> searchNewProducts(int count) {
		List<Product> products = new ArrayList<>();

		Product condition = new Product();

		try {
			products = productDao.selectByCondition(condition, 0, count);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			C3P0Utils.close();
		}

		return products;
	}

	@Override
	public void searchByCondition(Product condition, PageUtils<Product> pageUtils) {

		try {
			// 符合条件的总记录数
			int totalCount = productDao.countByCondition(condition);
			// 根据总记录数和每页显示的记录数，算出总页码
			int totalPages = totalCount / pageUtils.getPageSize();
			if (totalCount % pageUtils.getPageSize() != 0) {
				totalPages++;
			}

			pageUtils.setTotalCount(totalCount);
			pageUtils.setTotalPages(totalPages);

			if (pageUtils.getPageNo() <= 0) {
				pageUtils.setPageNo(1);
			} else if (pageUtils.getPageNo() > totalPages && totalPages > 0) {
				pageUtils.setPageNo(totalPages);
			}

			// 算出查询的起始下标
			int beginIndex = (pageUtils.getPageNo() - 1) * pageUtils.getPageSize();

			// 根据条件及分页信息，查询出指定页的数据集
			List<Product> selectByCondition = productDao.selectByCondition(condition, beginIndex,
					pageUtils.getPageSize());

			pageUtils.setData(selectByCondition);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			C3P0Utils.close();
		}

	}

	@Override
	public Product getById(String pid) {
		Product product = null;

		try {
			product = productDao.selectById(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			C3P0Utils.close();
		}

		return product;
	}

	@Override
	public List<Product> searchHistoryProducts(String[] pids) {
		List<Product> products = new ArrayList<>();

		try {
			for (int i = 0; i < pids.length; i++) {
				String id = pids[i];
				Product product = productDao.selectById(id);
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			C3P0Utils.close();
		}

		return products;
	}

	@Override
	public int doRemove(String pid) {
		int result = 0;

		try {
			result = productDao.delete(pid);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			C3P0Utils.close();
		}

		return result;
	}

}
