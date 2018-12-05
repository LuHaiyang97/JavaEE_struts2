package com.biz;

import com.bean.QueryObject;
import com.bean.User;
import com.dao.UserDao;
import com.dao.UserDaoImp;
import java.util.List;


public class UserBizImp implements UserBiz {
  UserDao ud = new UserDaoImp();
  
  /**
  * (non-Javadoc).
  * @see com.BIZ.UserBiz#getAllUser()
  */
  public List<User> getAllUser() {
    return ud.getAllUser();
  }
  
  /**
  * (non-Javadoc).
  * @see com.BIZ.UserBiz#deleteUserByID(int)
  */
  public int deleteUserById(User u) {
    return ud.deleteUserById(u);
  }
  
  /**
   * (non-Javadoc).
   */
  public int registeUser(User u) {
    return ud.registeUser(u);
  }

  /**
  * (non-Javadoc).
  * @see com.BIZ.UserBiz#userLogin(java.lang.String, java.lang.String)
  */
  public boolean userLogin(User u) {
    return ud.userLogin(u);
  }
  
  /**
   * (non-Javadoc).
   */
  public User selectUserById(User u) {
    return ud.selectUserById(u);
  }
  
  /**
   * (non-Javadoc).
   */
  public User selectUserByName(User u) {
    return ud.selectUserByName(u);
  }
  
  /**
   * (non-Javadoc).
   */
  public int updateUser(User u) {
    return ud.updateUser(u);
  }

  /**
   * (non-Javadoc).
   */
  public List<User> selUserBycondition(QueryObject qb) {
    return ud.selUserBycondition(qb);
  }

  /**
   * (non-Javadoc).
   */
  public List<User> pageShowUser(int page,int rows) {
    return ud.pageShowUser(page, rows);
  }
}
