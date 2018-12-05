package com.dao;

import com.bean.QueryObject;
import com.bean.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImp implements UserDao {
  Connection con = null;
  PreparedStatement pstmt = null;
  ResultSet rs = null;
  
  /**
  * 加载SQL.
  */
  private void prepareSql(String sql) throws SQLException {
    con = Database.getConnection();
    pstmt = con.prepareStatement(sql);
  }
  
  /**
  * 查询是否是已注册用户.
  */
  private boolean isUser(User u) {
    boolean f = false;
    String sql = "select * from tb_user where name=?";
    try {
      this.prepareSql(sql);
      pstmt.setString(1, u.getUserName());
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        f = true;
        System.out.println("用户已存在");
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      Database.close(rs, pstmt, con);
    }
    return f;
  }

  /**
  * 判断ID是否存在.
  */
  private boolean idExist(User u) {
    boolean f = false;
    String sql = "select * from tb_user where id=?";
    try {
      this.prepareSql(sql);
      pstmt.setInt(1, u.getUserId());
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        f = true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      Database.close(rs, pstmt, con);
    }
    return f;
  }

  /**
  * 给User对象赋值.
  */
  private User toUser(ResultSet rs) throws SQLException {
    User u = new User();
    u.setUserId(rs.getInt("id"));
    u.setUserName(rs.getString("name"));
    u.setUserPass(rs.getString("password"));
    u.setUserPhone(rs.getString("phone"));
    u.setUserAge(rs.getInt("age"));
    u.setUserPermission(rs.getString("permission"));
    return u;
  }

  /**
  * 条件查询判断条件.
   */
  private String judgeCondition(QueryObject qb) {
    StringBuffer sql = new StringBuffer("");
    if (qb.getQueryMethod().equals("precisely")) {
      sql.append("SELECT @rownum:=@rownum + 1 AS rownum, tb.* "
            + "FROM  library.tb_user tb ,(SELECT @rownum:= 0) r  where ");
      switch (qb.getQueryDestination()) {
        case 1:
          sql.append(" id = ?");
          break;
        case 2:
          sql.append(" name = ?");
          break;
        case 3:
          sql.append(" age = ?");
          break;
        case 4:
          sql.append(" permission = ?");
          break;
        default:
          break;
      }
    } else {
      sql.append("SELECT @rownum:=@rownum + 1 AS rownum, tb.* "
               + "FROM (SELECT @rownum:= 0) r ,(select s.* from(");
      String s = "select * from library.tb_user where ";
      sql.append(s + "id like ?  union "
               + s + "name like ? union "
               + s + "password like ? union "
               + s + "phone like ? union "
               + s + "age like ? union "
               + s + "permission like ?");
      sql.append(")s) tb");
    }
    return sql.toString();
  }
  
  /**
  * 动态数组接受全部用户信息.
  * @see com.user.DAO.UserDao#getAllUser()
  */
  public List<User> getAllUser() {
    List<User> list = new ArrayList<User>();
    String sql = "SELECT @rownum:=@rownum+1 AS rownum, tb.* "
                 + "FROM  tb_user tb ,(SELECT @rownum:=0) r ";
    try {
      this.prepareSql(sql);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        User u = new User();
        u = this.toUser(rs);
        u.setRowNum(rs.getInt("rownum"));
        list.add(u);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      Database.close(rs,pstmt,con);
    }
    return list;
  }

  /**
  * 通过ID删除用户.
  * @see com.DAO.UserDao#deleteUser(int)
  */
  public int deleteUserById(User u) {
    int result = 0;
    boolean flag = idExist(u);
    if (flag) {
      String sql = "delete  from tb_user where id=? ";
      try {
        this.prepareSql(sql);
        pstmt.setInt(1, u.getUserId());
        result = pstmt.executeUpdate();
      } catch (SQLException e) {
        e.printStackTrace();
      } finally {
        Database.close(rs, pstmt, con);
      }
    }
    return result;
  }


  /**
  * 注册用户.
  * @see com.DAO.UserDao#registeUser(java.lang.String, java.lang.String, java.lang.String)
  */ 
  public int registeUser(User u) {
    int result = 0;
    boolean flag = isUser(u);
    if (!flag) {
      String sql = "INSERT INTO library.tb_user(name,password,phone,age,permission)"
                  + "VALUES(?,?,?,?,?)";
      try {
        this.prepareSql(sql);
        pstmt.setString(1, u.getUserName());
        pstmt.setString(2,u.getUserPass());
        pstmt.setString(3, u.getUserPhone());
        pstmt.setInt(4, u.getUserAge());
        pstmt.setString(5,u.getUserPermission());
        result = pstmt.executeUpdate();
      } catch (SQLException e) {
        e.printStackTrace();
      } finally {
        Database.close(rs, pstmt, con);
      }
    }
    return result;
  }


  /**
  * 登录.
  * @see com.DAO.UserDao#userLogin(java.lang.String, java.lang.String)
  */
  public boolean userLogin(User u) {
    boolean f = false;
    String sql = "select * from tb_user where name=? and password=?";
    try {
      this.prepareSql(sql);
      pstmt.setString(1, u.getUserName());
      pstmt.setString(2, u.getUserPass());
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        f = true;
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      Database.close(rs, pstmt, con);
    }
    return f;
  }



  /**
  * 通过ID查询用户信息.
  * @see com.DAO.UserDao#selectUserById(int)
  */
  public User selectUserById(User u) {
    String sql = "select * from tb_user where id=?";
    User user = null;
    try {
      this.prepareSql(sql);
      pstmt.setInt(1, u.getUserId());
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        user = this.toUser(rs);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      Database.close(rs, pstmt, con);
    }
    return user;
  }

  /**
   * 通过用户名查询用户信息.
   */
  public User selectUserByName(User u) {
    String sql = "select * from tb_user where name=?";
    User user = null;
    try {
      this.prepareSql(sql);
      pstmt.setString(1, u.getUserName());
      ResultSet rs = pstmt.executeQuery();
      if (rs.next()) {
        user = this.toUser(rs);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      Database.close(rs, pstmt, con);
    }
    return user;
  }

  /**
   * 修改.
  * @see com.DAO.UserDao#updateUser(com.Bean.User)
  * 
  */
  public int updateUser(User u) {
    String sql = "update tb_user set name=?,password=?,phone=? where id=?";
    int result = 0;
    boolean f = this.isUser(u);
    if (!f) {
      try {
        this.prepareSql(sql);
        pstmt.setString(1, u.getUserName());
        pstmt.setString(2, u.getUserPass());
        pstmt.setString(3, u.getUserPhone());
        pstmt.setInt(4, u.getUserId());
        result = pstmt.executeUpdate();
      } catch (SQLException e) {
        e.printStackTrace();
      } finally {
        Database.close(rs, pstmt, con);
      }      
    }
    return result;
  }
  
  /**
   * 条件查询.
  * @see com.DAO.UserDao#selUserBycondition(com.Bean.QueryObject)
  */
  public List<User> selUserBycondition(QueryObject qb) {
    List<User> list = new ArrayList<User>();
    String sql = this.judgeCondition(qb);
    try {
      this.prepareSql(sql);
      if (qb.getQueryMethod().equals("precisely")) {
        pstmt.setString(1, qb.getQueryValue());
      } else {
        for (int i = 1;i < 7;i++) {
          pstmt.setString(i, "%" + qb.getQueryValue() + "%");
        }
      }
      rs = pstmt.executeQuery();
      while (rs.next()) {
        User u = new User();
        u = this.toUser(rs);
        u.setRowNum(rs.getInt("rownum"));
        list.add(u);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      Database.close(rs, pstmt, con);
    }
    return list;
  }
  
  /**
   * 分页显示.
   */
  public List<User> pageShowUser(int page, int rows) {
    List<User> list = new ArrayList<User>();
    String sql = "SELECT @rownum:=@rownum+1 AS rownum, tb.* FROM " 
                 + " tb_user tb ,(SELECT @rownum:=0) r limit ? , ?";
    try {
      this.prepareSql(sql);
      pstmt.setInt(1, (page - 1) * rows + 1);
      pstmt.setInt(2, page * rows);
      rs = pstmt.executeQuery();
      while (rs.next()) {
        User u = new User();
        u = this.toUser(rs);
        u.setRowNum(rs.getInt("rownum"));
        list.add(u);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      Database.close(rs,pstmt,con);
    }
    return list;
  }

}
