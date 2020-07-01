package com.aia.base.shiro.realm;

import com.baomidou.mybatisplus.annotation.TableName;

@TableName("sys_user")
public class SysUser {

    private String userName;

    private String password;

    private String userId;

    public SysUser(){

    }
    public SysUser(String userName,String password){
        this.userName=userName;
        this.password=password;
    }

    public void setUserName(String userName){
        this.userName =userName;
    }

    public void setPassword(String password){
        this.password =password;
    }

    public String getUserName(){
        return userName;
    }

    public String getPassword(){
        return password;
    }
}
