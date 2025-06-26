package com.igeek.shop.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * @ClassName: C3P0Utils
 * @Description: C3P0Utils工具类
 * @date 2018年11月12日 上午8:49:49
 * Company www.igeekhome.com
 *
 */
public class C3P0Utils {

    //创建连接池对象
    private static ComboPooledDataSource dataSource = null;

    //本地线程池:缓存连接对象
    private static ThreadLocal<Connection> threadLocal = null;

    //静态初始化
    public static void init() {
        dataSource = new ComboPooledDataSource();
        threadLocal = new ThreadLocal<Connection>();
    }

    /**
     *
     * @Title: getDataSource
     * @Description: 获取连接池对象
     * @return
     */
    public static DataSource getDataSource() {

        return dataSource;
    }

    /**
     *
     * @Title: getConnection
     * @Description: 获取连接
     * @return
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {

        //先尝试从本地线程池中获取连接对象
        Connection connection = threadLocal.get();

        if(connection==null){
            //如果是第一次调用，则本地线程池中没有现成的连接对象
            //则从连接池中获取一个新的连接对象
            connection = dataSource.getConnection();

            //并把此连接对象放入到本地线程池中，以便同一线程中后续操作可以反复获取同一个连接对象
            threadLocal.set(connection);
        }

        return connection;
    }

    /**
     * 关闭连接
     */
    public static void close() {
        //先尝试从本地线程池中获取连接对象
        Connection connection = threadLocal.get();
        if(connection!=null) {
            try {
                connection.close();
                //要把关闭的连接从本地线程池中移除
                threadLocal.remove();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     *
     * @Title: close
     * @Description: 关闭资源
     * @param rs
     * @param stmt
     * @param connection
     */
    public static void close(ResultSet rs, Statement stmt, Connection connection) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    //释放资源
    public static void destory() {
        dataSource.close();
    }
}
