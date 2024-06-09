package com.example.btlexample.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class ConnectionPoolImpl implements ConnectionPool {
    private String driver;
    private String username;
    private String password;
    private String url;
    private Stack<Connection> pool;

    public ConnectionPoolImpl() {
        //driver identification
        this.driver = "com.mysql.cj.jdbc.Driver";
        //load driver
        try {
            Class.forName(this.driver);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            throw new RuntimeException();
        }
        //account
        this.username = "huwng";
        this.password = "hungkenh";
        //database url
        this.url = "jdbc:mysql://127.0.0.1:3306/2023it6020_btl?allowMultiQueries=true";
        this.pool = new Stack<>();
    }

    @Override
    public Connection getConnection(String objectName) throws SQLException {
        if (this.pool.isEmpty()) {
            System.out.println(objectName + " init'd a new connection");
            Connection connection = DriverManager.getConnection(this.url,this.username,this.password);
            this.pool.push(connection);
            return connection;
        } else {
            System.out.println(objectName + " retrieved a connection");
            return this.pool.pop();
        }
    }

    @Override
    public void releaseConnection(Connection con, String objectName) {
        System.out.println(objectName+" released a connection");
        this.pool.push(con);
    }

    @SuppressWarnings("removal")
    @Override
    protected void finalize() throws Throwable {
        try {
            this.pool.clear();
            this.pool=null;
            System.out.println("CPool closed!");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            super.finalize();
        }
    }
}
