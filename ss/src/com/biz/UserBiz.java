package com.biz;

import com.bean.QueryObject;
import com.bean.User;
import java.util.List;

public interface UserBiz {
  public List<User> getAllUser();
  
  public boolean userLogin(User u);
  
  public int deleteUserById(User u);
  
  public int registeUser(User u);
  
  public User selectUserById(User u);
  
  public User selectUserByName(User u);
  
  public int updateUser(User u);
  
  public List<User> selUserBycondition(QueryObject qb);
  
  public List<User> pageShowUser(int page,int rows);
}
