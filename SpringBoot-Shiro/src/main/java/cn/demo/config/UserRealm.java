package cn.demo.config;

import cn.demo.entry.User;
import cn.demo.service.IUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Classname UserRealm
 * @Description 自定义UserRealm
 * @Date 2020/8/25 23:56
 * @Created by XJC·AW
 */
public class UserRealm extends AuthorizingRealm {

    @Autowired
    IUserService userService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了doGetAuthorizationInfo >>>>> 授权");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //info.addStringPermission("user:add");
        //获取当前登录的对象
        Subject subject = SecurityUtils.getSubject();
        User currentUser = (User) subject.getPrincipal(); //拿到user对象
        //设置当前用户权限
        info.addStringPermission(currentUser.getPerms());

        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了doGetAuthenticationInfo >>>>> 认证");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        User user = userService.findUserByName(usernamePasswordToken.getUsername());
        if(user == null){
            return  null;
        }

        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        session.setAttribute("loginUser",user);

        //密码认证
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");
    }
}
