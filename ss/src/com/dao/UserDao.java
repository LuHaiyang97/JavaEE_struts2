package com.dao;

import com.bean.QueryObject;
import com.bean.User;
import java.util.List;

public interface UserDao {
  
  //列出所有用户信息
  public List<User> getAllUser();
  
  //分页列出所有用户信息
  public List<User> pageShowUser(int page,int rows);
  
  //登录
  public boolean userLogin(User u);
  
  //删除用户
  public int deleteUserById(User u);
  
  //用户注册
  public int registeUser(User u);
  
  //根据ID查询用户
  public User selectUserById(User u);
  
  //根据用户名查询用户
  public User selectUserByName(User u);
  
  //修改用户信息
  public int updateUser(User u);
  
  //条件查询
  public List<User> selUserBycondition(QueryObject qb);
}
