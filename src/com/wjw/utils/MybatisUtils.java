package com.wjw.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.Reader;

/**
 * @author 吴嘉炜
 */
public class MybatisUtils {
    private static SqlSessionFactory sqlSessionFactory = null;

    //初始化
    static {
        try {
            //使用Mybatis提供的Resource类加载Mybatis的配置文件
            Reader reader = Resources.getResourceAsReader("mybatis-config.xml");
            //构建sqlSessionFactory工厂
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取SqlSession对象的静态方法
     */
    public static SqlSession getSession() {
        return sqlSessionFactory.openSession();
    }
}
