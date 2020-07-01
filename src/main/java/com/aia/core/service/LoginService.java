package com.aia.core.service;

import com.aia.base.shiro.realm.SysUser;
import com.aia.core.dao.LoginMapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService implements ILoginService {
    @Autowired
    LoginMapper loginMapper;
    @Override
    public SysUser login(String username, String password) {
        SysUser user =new SysUser(username,password);
        QueryWrapper<SysUser> queryMapper =new QueryWrapper<>();
        queryMapper.eq("user_name",user.getUserName())
                   .eq("password",user.getPassword());
        return loginMapper.selectOne(queryMapper);
    }
}
