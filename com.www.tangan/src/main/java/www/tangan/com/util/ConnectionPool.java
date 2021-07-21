package www.tangan.com.util;

import www.tangan.com.adapter.ConnectionPoolAdapter;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Proxy;
import java.sql.*;
import java.util.LinkedList;
import java.util.Properties;

import static www.tangan.com.util.Constant.METHOD_CLOSE;

/**
 * @author 谭淦
 */
public class ConnectionPool extends ConnectionPoolAdapter {

    private static  LinkedList<Connection>  connPool = new LinkedList<>();

    static {
        InputStream in = ConnectionPool.class.getClassLoader().getResourceAsStream("pro.properties");
        Properties prop = new Properties();
        try {
            prop.load(in);
            String driver = prop.getProperty("driverClassName");
            String url = prop.getProperty("url");
            String user = prop.getProperty("username");
            String password = prop.getProperty("password");
            //数据库连接池的初始化连接数的大小
            int initSize = Integer.parseInt(prop.getProperty("initialSize"));
            Class.forName(driver);
            for (int i = 0; i < initSize; i++) {
                Connection conn = DriverManager.getConnection(url, user, password);
                //将创建的连接添加的list中
                System.out.println("初始化数据库连接池，创建第 " + (i + 1) + " 个连接，添加到池中");
                connPool.add(conn);
            }

        } catch (IOException | SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Connection getConnection() {
        if (connPool.size() > 0) {
            //从这集合里面获取一个连接 ,同时获取一个连接后connPool集合少一个
            Connection conn = connPool.removeFirst();
            //返回Connection的代理对象
            return (Connection) Proxy.newProxyInstance(ConnectionPool.class.getClassLoader(), conn.getClass().getInterfaces(), (proxy, method, args) -> {
                if (METHOD_CLOSE.equals(method.getName())) {
                    connPool.add(conn);
                    System.out.println("关闭连接，实际还给了数据库连接池");
                    System.out.println("数据库连接池的现存个数为" + connPool.size());
                    return null;
                } else {
                    return method.invoke(conn, args);
                }
            });
        } else {
            throw new RuntimeException("数据库繁忙，稍后再试");
        }
    }


}
