package com.aia.base.shiro.realm;

import com.aia.core.service.ILoginService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 自定义Realm 处理登录 权限
 */
public class UserRealm extends AuthorizingRealm {
    private static final Logger log = LoggerFactory.getLogger(UserRealm.class);

    @Autowired
    ILoginService loginService;
    /**
     * 授权
     */
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
//        SysUser user = ShiroUtils.getSysUser();
//        // 角色列表
//        Set<String> roles = new HashSet<String>();
//        // 功能列表
//        Set<String> menus = new HashSet<String>();
//        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        // 管理员拥有所有权限
//        if (user.isAdmin()) {
//            info.addRole("admin");
//            info.addStringPermission("*:*:*");
//        } else {
//            roles = roleService.selectRoleKeys(user.getUserId());
//            menus = menuService.selectPermsByUserId(user.getUserId());
//            // 角色加入AuthorizationInfo认证对象
//            info.setRoles(roles);
//            // 权限加入AuthorizationInfo认证对象
//            info.setStringPermissions(menus);
//        }
//        return info;
//    }

    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        String password = "";
        if (upToken.getPassword() != null) {
            password = new String(upToken.getPassword());
        }
        SysUser user = null;
        try {
            user = loginService.login(username, password);
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user, password, getName());
            return info;
        }catch(Exception e){
            throw new AuthenticationException(e.getMessage(), e);
        }

    }

    /**
     * 清理缓存权限
     */
    public void clearCachedAuthorizationInfo() {
        this.clearCachedAuthorizationInfo(SecurityUtils.getSubject().getPrincipals());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
