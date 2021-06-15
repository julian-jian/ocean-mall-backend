/**
  * Copyright 2020 bejson.com 
  */
package com.macro.mall.entity;
import java.util.List;

/**
 * Auto-generated: 2020-07-30 16:16:5
 *
 * @author bejson.com (i@bejson.com)
 * @website http://www.bejson.com/java2pojo/
 */
public class Showapi_res_body {

    private String mailNo;
    private int ret_code;
    private boolean flag;
    private int status;
    private String tel;
    private String expSpellName;
    private List<Data> data;
    private String expTextName;
    public void setMailNo(String mailNo) {
         this.mailNo = mailNo;
     }
     public String getMailNo() {
         return mailNo;
     }

    public void setRet_code(int ret_code) {
         this.ret_code = ret_code;
     }
     public int getRet_code() {
         return ret_code;
     }

    public void setFlag(boolean flag) {
         this.flag = flag;
     }
     public boolean getFlag() {
         return flag;
     }

    public void setStatus(int status) {
         this.status = status;
     }
     public int getStatus() {
         return status;
     }

    public void setTel(String tel) {
         this.tel = tel;
     }
     public String getTel() {
         return tel;
     }

    public void setExpSpellName(String expSpellName) {
         this.expSpellName = expSpellName;
     }
     public String getExpSpellName() {
         return expSpellName;
     }

    public void setData(List<Data> data) {
         this.data = data;
     }
     public List<Data> getData() {
         return data;
     }

    public void setExpTextName(String expTextName) {
         this.expTextName = expTextName;
     }
     public String getExpTextName() {
         return expTextName;
     }

}