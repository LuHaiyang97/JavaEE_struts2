package com.action;

import com.bean.QueryObject;
import com.bean.User;
import com.biz.UserBizImp;
import com.opensymphony.xwork2.ActionSupport;

import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

public class UserAction extends ActionSupport {

  private static final long serialVersionUID = 1;


  private List<User>         list;
  private User              users;
  private QueryObject queryObject;
  private String rePass;
  HttpSession session = ServletActionContext.getRequest().getSession(); 
  
  public List<User> getList() {
    return list;
  }
  
  public void setList(List<User> list) {
    this.list = list;
  }
  
  public User getUsers() {
    return users;
  }
  
  public void setUsers(User users) {
    this.users = users;
  }
  
  public QueryObject getQueryObject() {
    return queryObject;
  }
  
  public void setQueryObject(QueryObject queryObject) {
    this.queryObject = queryObject;
  }  
  
  public String getRePass() {
    return rePass;
  }

  public void setRePass(String rePass) {
    this.rePass = rePass;
  }

  /**
   * 跳向登录.
   * @return
   */
  public String toLogin()throws Exception {
    return SUCCESS;
  }
  
  /**
   * 跳向首页.
   * @return
   */
  public String toIndex()throws Exception {
    return SUCCESS;
  }
  
  /**
   * 查询全部用户信息.
   * 方法详细说明
   * @return
   */  
  public String selectAllUser() throws Exception {
    UserBizImp ub = new UserBizImp();
    list = ub.getAllUser();
    return "success";
  }
  
  /**
   * 条件查询.
   * 方法详细说明
   * @return
   */   
  public String selUserByCondition()throws Exception {
    UserBizImp ub = new UserBizImp();
    list = ub.selUserBycondition(queryObject);
    return "success";
  }
  
  /**
   * 登录.
   * 方法详细说明
   * @return
   */ 
  public String login()throws Exception {
    UserBizImp ub = new UserBizImp();
    if (ub.userLogin(users)) {
      this.users = ub.selectUserByName(users);
      session.setAttribute("user", users);
      return SUCCESS;
    } else {
      return "gg";
    }
  }
  
  /**
   * 注销.
   * 方法详细说明
   * @return
   */ 
  public String logout()throws Exception {
    session.removeAttribute("user");
    return SUCCESS;
  }
  
  /**
   * 删除用户.
   * 方法详细说明
   * @return
   */ 
  public String delete()throws Exception {
    UserBizImp ub = new UserBizImp();
    if (ub.deleteUserById(users) != 0) {
      return this.selectAllUser();
    } else {
      return ERROR;
    }
  }

  /**
   * 跳向注册.
   * 方法详细说明
   * @return
   */ 
  public String toRegiste()throws Exception {
    return SUCCESS;
  }
  
  /**
   * 注册.
   * 方法详细说明
   * @return
   */ 
  public String registe()throws Exception {
    UserBizImp ub = new UserBizImp();
    if (ub.registeUser(users) != 0) {
      return this.selectAllUser();
    } else {
      return INPUT;
    }
  }

  /**
   * 跳向修改.
   * 方法详细说明
   * @return
   */ 
  public String toUpdate()throws Exception {
    UserBizImp ub = new UserBizImp();
    users = ub.selectUserById(users);
    return SUCCESS;
  }

  /**
   * 修改.
   * 方法详细说明
   * @return SUCCESS or INPUT
   * 
   */ 
  public String update()throws Exception {
    UserBizImp ub = new UserBizImp();
    int i = ub.updateUser(users);
    if (i != 0) {
      return this.selectAllUser();
    } else {
      return INPUT;
    }
  }
  
  /**
  * 对注册进行数据校验.
  *
  public void validateRegiste() {
    if ("".equals(users.getUserName()) || users.getUserName() == null) {
      this.addFieldError("users.userName", "用户名不能为空");
    }
    else if (users.getUserName().length() < 5 || users.getUserName().length() > 12) {
      this.addFieldError("users.userName", "用户名长度在5-12之间");
    }
    if ("".equals(users.getUserPass()) || users.getUserPass() == null) {
      this.addFieldError("users.userPass", "密码不能为空");
    }
    else if (users.getUserPass().length() < 5 || users.getUserPass().length() > 10) {
      this.addFieldError("users.userPass", "密码长度在5-10之间");
    }
    if (users.getRePass() != users.getUserPass()) {
      this.addFieldError("users.rePass", "确认密码必须与密码相同");
    }
    if (users.getUserPhone().length() != 11) {
      this.addFieldError("users.userPhone", "手机号长度为11位");
    }
    if (users.getUserAge() == 0) {
      this.addFieldError("users.userAge", "输入正确的年龄");
    }
    if ("".equals(users.getUserBirthday())) {
      this.addFieldError("users.userBirthday", "出生日期不能为空");
    } else {
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
      Date date = new Date();
      String str = df.format(date);
      Date bir = new Date();
      try {
        bir = df.parse(users.getUserBirthday());
      } catch (ParseException e) {
        e.printStackTrace();
      }
      String gg = df.format(bir);
      int f = gg.compareTo(str);
      if (f >= 0) {
        this.addFieldError("users.userBirthday", "输入正确的出生日期");
      }
    }
    if (users.getUserPermission() == null) {
      this.addFieldError("users.userPermission", "选择一个身份");
    }
  }
  */
}
