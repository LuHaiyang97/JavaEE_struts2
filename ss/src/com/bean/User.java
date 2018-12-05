package com.bean;



public class User {

  private int    rowNum;
  private int    userId;
  private String userName;
  private String userPass;
  private String userPhone;
  private int    userAge;
  private String userBirthday;
  private String userPermission;
  private int    page;
  private int    rows;
  
  public int getRowNum() {
    return rowNum;
  }
  
  public void setRowNum(int rowNum) {
    this.rowNum = rowNum;
  }
  
  public int getUserId() {
    return userId;
  }
  
  public void setUserId(int id) {
    userId = id;
  }
  
  public String getUserName() {
    return userName;
  }
  
  public void setUserName(String userName) {
    this.userName = userName;
  }
  
  public String getUserPass() {
    return userPass;
  }
  
  public void setUserPass(String userPass) {
    this.userPass = userPass;
  }
  
  public String getUserPhone() {
    return userPhone;
  }
  
  public void setUserPhone(String userPhone) {
    this.userPhone = userPhone;
  }
  
  public int getUserAge() {
    return userAge;
  }
  
  public void setUserAge(int userAge) {
    this.userAge = userAge;
  }
    
  public String getUserBirthday() {
    return userBirthday;
  }

  public void setUserBirthday(String userBirthday) {
    this.userBirthday = userBirthday;
  }

  public String getUserPermission() {
    return userPermission;
  }
  
  public void setUserPermission(String userPermission) {
    this.userPermission = userPermission;
  }
  
  public int getPage() {
    return page;
  }
  
  public void setPage(int page) {
    this.page = page;
  }
  
  public int getRows() {
    return rows;
  }
  
  public void setRows(int rows) {
    this.rows = rows;
  }
}
