package com.igeek.shop.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

public class MyBatisUtils {

    private static final String RESOURCE = "mybatis/mybatis-config.xml";
    private static Reader reader = null;
    private static SqlSessionFactory sqlSessionFactory = null;

    //自动加载执行一次
    static {
        try {
            // 使用MyBatis提供的Resources类加载mybatis的配置文件（它也加载关联的映射文件）
            reader = Resources.getResourceAsReader(RESOURCE);
            SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
            // 构建sqlSession的工厂,指定使用work环境配置来构建工厂
            //sqlSessionFactory = sqlSessionFactoryBuilder.build(reader,"work");
            // 默认加载的是default指定的连接环境配置
            sqlSessionFactory = sqlSessionFactoryBuilder.build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static SqlSession getSession() {
        // 创建能执行映射文件中sql的sqlSession
        return sqlSessionFactory.openSession();
    }

    public static SqlSession getSession(boolean commit) {
        // 创建能执行映射文件中sql的sqlSession，并且完成CRUD后，是否提交数据
        // true为自动提交
        // false则需要手动提交，sqlSession.commit()
        return sqlSessionFactory.openSession(commit);
    }

    public static void close(SqlSession session) {
        //关闭SqlSession资源
        if (session != null) {
            session.close();
        }
    }


}
