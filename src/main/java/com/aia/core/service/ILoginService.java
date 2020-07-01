package com.aia.core.service;

import com.aia.base.shiro.realm.SysUser;

public interface ILoginService {
    SysUser login(String username, String password);
}
