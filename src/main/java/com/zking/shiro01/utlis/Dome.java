package com.zking.shiro01.utlis;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;

import java.util.ArrayList;
import java.util.List;

public class Dome {
    public static void main(String[] args) {
        //1、创建SecurityManagerFactory工厂类
        IniSecurityManagerFactory factory=new IniSecurityManagerFactory("classpath:shiro-permission.ini");
        //2、创建SecurityManager
        SecurityManager securityManager = factory.getInstance();
        //3、将安全管理器交由SecurityUtils
        SecurityUtils.setSecurityManager(securityManager);
        //4、获取主体Subject
        Subject subject = SecurityUtils.getSubject();
        //5、创建登陆令牌Token
        UsernamePasswordToken token=new UsernamePasswordToken(
          "admin",
          "123"
        );
        //6、身份验证
        //异常
        //org.apache.shiro.authc.UnknownAccountException //账号错误
        //org.apache.shiro.authc.IncorrectCredentialsException //密码错误
        //org.apache.shiro.authz.UnauthorizedException 无权限
        try {
            subject.login(token);
            System.out.println("身份验证成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //7、角色认证
        try {
            //判断是否有角色
            if (subject.hasRole("role2")) {
                System.out.println("角色认证成功");
            }else{
                System.out.println("角色认证失败");
            }
            //无返回值 没权限报异常
            //subject.checkRole("role2");
            //接收参数集合  返回值：一个没有则fasle 全部成立true
           // subject.hasAllRoles();
            //接收参数集合 返会值数组 对应返回true|false
            //subject.hasRoles();
        } catch (Exception e) {
            e.printStackTrace();
        }

        //8、权限认证
        try {
            if (subject.isPermitted("system:user:delete")) {
                System.out.println("权限验证成功");
            } else {
                System.out.println("权限验证失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        //9、安全退出
        subject.logout();
    }
}
