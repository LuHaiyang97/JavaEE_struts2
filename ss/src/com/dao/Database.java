package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Database {
  /**
  * 取得连接.
  */
  public static Connection getConnection() {
    Connection conn = null;
    try {
      //定义驱动
      String driver = "com.mysql.cj.jdbc.Driver";
      /*
      * String driver="sun.jdbc.odbc.JdbcOdbcDriver";
      */

      Class.forName(driver);
      //连接的数据库
      String url = "jdbc:mysql://localhost:3306/library?useSSL=false";
      //数据库用户名

      String user = "debian-sys-maint";

      //数据库密码

      String pass = "zUjdSaCoAOPbwB8k";

      /*
      * ODBC连接
      * String OdbcUrl="jdbc:odbc:数据源名";
      * conn = DriverManager.getConnection(OdbcUrl);
      */

      conn = DriverManager.getConnection(url,user,pass);      
    } catch (ClassNotFoundException e) {
      System.out.println("加载驱动失败！");
      e.printStackTrace();
    } catch (SQLException e) {
      System.out.println("连接失败！");
      e.printStackTrace();
    }
    return conn;
  }

  /**
  * 关闭.
  */
  public static void close(ResultSet rs, PreparedStatement pstmt,Connection conn) {
    try {
      if (null != rs) {
        rs.close();
      }
      if (null != pstmt) {
        pstmt.close();
      }
      if (null != conn) {
        conn.close();
      }
    } catch (Exception e) {
      System.out.println("关闭连接失败");
      e.printStackTrace();
    }
  }

}
