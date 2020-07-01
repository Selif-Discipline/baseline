package com.aia.core;

import com.aia.base.StringUtils;
import com.aia.base.shiro.realm.SysUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class SysLoginController {

    @PostMapping(value="/login", produces = {"application/json;charset=UTF-8"},consumes ={"application/json;charset=UTF-8"})
    @ResponseBody
    public String login( @RequestBody SysUser user){
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword(), false);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            SecurityUtils.getSubject().getSession().setTimeout(1800000);
            return "success"+subject.getSession().getId();
        } catch (AuthenticationException e) {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage())) {
                msg = e.getMessage();
            }
            return "fail";
        }
    }
}
