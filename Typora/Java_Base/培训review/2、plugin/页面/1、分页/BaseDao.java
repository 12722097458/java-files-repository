package com.igeek.shop.dao;

import java.sql.SQLException;
import java.util.*;

public interface BaseDao<T> {
	public abstract List<T> selectAll() throws SQLException;
	
	public abstract List<T> selectByCondition(T condition,int beginIndex,int count) throws SQLException;
	public abstract int countByCondition(T condition) throws SQLException;
	
	public abstract T selectById(Integer id) throws SQLException;
	public abstract T selectById(String id) throws SQLException;
	public abstract int insert(T bean) throws SQLException;
	public abstract int delete(Integer id) throws SQLException;
	public abstract int delete(String id) throws SQLException;
	public abstract int update(T bean) throws SQLException;
}
