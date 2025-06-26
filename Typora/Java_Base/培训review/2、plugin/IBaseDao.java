package com.igeek.shop.dao;

import java.sql.SQLException;
import java.util.*;

public interface IBaseDao<T> {
	public abstract List<T> selectAll() throws SQLException;
	
	public abstract List<T> selectByCondition(T condition,int beginIndex,int pageSize) throws SQLException;
	public abstract Integer countByCondition(T condition) throws SQLException;
	
	public abstract T selectById(Integer id) throws SQLException;
	public abstract T selectById(String id) throws SQLException;
	public abstract int insert(T t) throws SQLException;
	public abstract int delete(Integer id) throws SQLException;
	public abstract int delete(String id) throws SQLException;
	public abstract int update(T t) throws SQLException;
}
