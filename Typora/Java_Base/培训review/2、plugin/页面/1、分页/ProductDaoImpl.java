package com.igeek.shop.dao.mysql;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.igeek.shop.dao.ProductDao;
import com.igeek.shop.entity.Product;
import com.igeek.shop.utils.C3P0Utils;

public class ProductDaoImpl implements ProductDao {

	@Override
	public List<Product> selectAll() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> selectByCondition(Product condition, int beginIndex, int count) throws SQLException {
		List<Product> products = new ArrayList<>();

		String sql = "select p.pid,p.pname,p.market_price,p.shop_price,p.pimage,p.pdate,p.is_hot,p.pdesc,p.pflag,c.cid,c.cname from product p left join category c on p.cid=c.cid [where] order by p.pdate desc limit ?,?";

		List paramsList = new ArrayList<>();
		String where = "";
		if (condition != null) {
			if (condition.getIs_hot() != null) {
				// 组装条件语句的同时，把对应位置上的？数据存入到paramsList中
				where += " and p.is_hot=? ";
				paramsList.add(condition.getIs_hot());
			}
			
			if (condition.getCid() != null&&!"".equals(condition.getCid())) {
				// 组装条件语句的同时，把对应位置上的？数据存入到paramsList中
				where += " and p.cid=? ";
				paramsList.add(condition.getCid());
			}
			
			if (condition.getPname() != null&&!"".equals(condition.getPname())) {
				// 组装条件语句的同时，把对应位置上的？数据存入到paramsList中
				where += " and p.pname like ? ";
				paramsList.add("%"+condition.getPname()+"%");
			}
			
			if (condition.getBeginPrice() != null&&condition.getBeginPrice()>0) {
				// 组装条件语句的同时，把对应位置上的？数据存入到paramsList中
				where += " and p.shop_price >= ? ";
				paramsList.add(condition.getBeginPrice());
			}
			
			if (condition.getEndPrice() != null&&condition.getEndPrice()>0) {
				// 组装条件语句的同时，把对应位置上的？数据存入到paramsList中
				where += " and p.shop_price <= ? ";
				paramsList.add(condition.getEndPrice());
			}
			
			if (condition.getBeginDate() != null) {
				// 组装条件语句的同时，把对应位置上的？数据存入到paramsList中
				where += " and p.pdate >= ? ";
				paramsList.add(condition.getBeginDate());
			}
			
			if (condition.getEndDate()!=null) {
				// 组装条件语句的同时，把对应位置上的？数据存入到paramsList中
				where += " and p.pdate <= ? ";
				paramsList.add(condition.getEndDate());
			}

			where = where.replaceFirst("and", "where");
		}

		// 用实际的条件sql语句，替代自定义[where]条件占位符
		sql = sql.replace("[where]", where);

		paramsList.add(beginIndex);
		paramsList.add(count);
		
		System.out.println(sql);

		Object[] params = paramsList.toArray();

		QueryRunner qr = new QueryRunner();

		products = qr.query(C3P0Utils.getConnection(), sql, new BeanListHandler<Product>(Product.class), params);

		return products;
	}
	
	@Override
	public int countByCondition(Product condition) throws SQLException {
		int count = 0;
		
		String sql = "select count(p.pid) from product p [where]";

		List paramsList = new ArrayList<>();
		String where = "";
		if (condition != null) {
			if (condition.getIs_hot() != null) {
				// 组装条件语句的同时，把对应位置上的？数据存入到paramsList中
				where += " and p.is_hot=? ";
				paramsList.add(condition.getIs_hot());
			}
			
			if (condition.getCid() != null&&!"".equals(condition.getCid())) {
				// 组装条件语句的同时，把对应位置上的？数据存入到paramsList中
				where += " and p.cid=? ";
				paramsList.add(condition.getCid());
			}
			
			if (condition.getPname() != null&&!"".equals(condition.getPname())) {
				// 组装条件语句的同时，把对应位置上的？数据存入到paramsList中
				where += " and p.pname like ? ";
				paramsList.add("%"+condition.getPname()+"%");
			}
			
			if (condition.getBeginPrice() != null&&condition.getBeginPrice()>0) {
				// 组装条件语句的同时，把对应位置上的？数据存入到paramsList中
				where += " and p.shop_price >= ? ";
				paramsList.add(condition.getBeginPrice());
			}
			
			if (condition.getEndPrice() != null&&condition.getEndPrice()>0) {
				// 组装条件语句的同时，把对应位置上的？数据存入到paramsList中
				where += " and p.shop_price <= ? ";
				paramsList.add(condition.getEndPrice());
			}
			
			if (condition.getBeginDate() != null) {
				// 组装条件语句的同时，把对应位置上的？数据存入到paramsList中
				where += " and p.pdate >= ? ";
				paramsList.add(condition.getBeginDate());
			}
			
			if (condition.getEndDate()!=null) {
				// 组装条件语句的同时，把对应位置上的？数据存入到paramsList中
				where += " and p.pdate <= ? ";
				paramsList.add(condition.getEndDate());
			}

			where = where.replaceFirst("and", "where");
		}

		// 用实际的条件sql语句，替代自定义[where]条件占位符
		sql = sql.replace("[where]", where);

		System.out.println(sql);

		Object[] params = paramsList.toArray();

		QueryRunner qr = new QueryRunner();

		Long longCount = qr.query(C3P0Utils.getConnection(), sql, new ScalarHandler<Long>(), params);
		
		count = longCount.intValue();
		
		return count;
	}

	@Override
	public Product selectById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product selectById(String id) throws SQLException {
		Product product = null;
		
		String sql = "select pid,pname,market_price,shop_price,pimage,pdate,is_hot,pdesc,pflag,cid from product where pid=?";

		Object[] params = {id};

		QueryRunner qr = new QueryRunner();

		product = qr.query(C3P0Utils.getConnection(), sql, new BeanHandler<Product>(Product.class), params);

		return product;
	}

	@Override
	public int insert(Product t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(Integer id) throws SQLException {
		int result =0;

		return result;
	}

	@Override
	public int delete(String id) throws SQLException {
		int result =0;
		
		String sql = "delete from product where pid=?";

		Object[] params = {id};

		QueryRunner qr = new QueryRunner();

		result = qr.update(C3P0Utils.getConnection(), sql, params);

		return result;
	}

	@Override
	public int update(Product t) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	

}
