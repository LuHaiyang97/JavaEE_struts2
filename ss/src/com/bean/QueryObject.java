package com.bean;

public class QueryObject {

  private String queryMethod;
  private int queryDestination;
  private String queryValue;
  
  public String getQueryMethod() {
    return queryMethod;
  }
  
  public int getQueryDestination() {
    return queryDestination;
  }

  public void setQueryDestination(int queryDestination) {
    this.queryDestination = queryDestination;
  }

  public String getQueryValue() {
    return queryValue;
  }

  public void setQueryValue(String queryValue) {
    this.queryValue = queryValue;
  }

  public void setQueryMethod(String queryMethod) {
    this.queryMethod = queryMethod;
  }
  
}
