package com.zking.shiro01.servlet;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取登陆账号密码
        String username=req.getParameter("username");
        String password=req.getParameter("password");

        //获取主体Subject
        Subject subject = SecurityUtils.getSubject();

        //创建登陆令牌
        UsernamePasswordToken token=new UsernamePasswordToken(
          username,
          password
        );

        try {
            subject.login(token);
            req.getSession().setAttribute("username",username);
            resp.sendRedirect(req.getContextPath()+"/main.jsp");
        } catch (AuthenticationException e) {
            e.printStackTrace();
            req.setAttribute("message","认证失败");
            req.getRequestDispatcher("login.jsp").forward(req,resp);
        }
    }
}
