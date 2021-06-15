/**
  * Copyright 2020 bejson.com
  */
package com.macro.mall.entity;
import java.util.Date;


public class Data {

    private Date time;
    private String context;
    public void setTime(Date time) {
         this.time = time;
     }
     public Date getTime() {
         return time;
     }

    public void setContext(String context) {
         this.context = context;
     }
     public String getContext() {
         return context;
     }

}
