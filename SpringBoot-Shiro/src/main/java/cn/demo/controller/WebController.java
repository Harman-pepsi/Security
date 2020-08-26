package cn.demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Classname WebController
 * @Description TODO
 * @Date 2020/8/26 0:10
 * @Created by XJC·AW
 */
@Controller
public class WebController {

    @RequestMapping({"/","/index"})
    public String toIndex(Model model){
        model.addAttribute("msg","Hello Shiro");
        return "index";
    }

    @RequestMapping("/user/add")
    public String toAdd(){
        return "user/add";
    }

    @RequestMapping("/user/update")
    public String toUpdate(){
        return "user/update";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/toLogin")
    public String toLogin(String username,String password,Model model){
        //获得当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            subject.login(token); //执行登录的方法如果没有异常就说明正常
            return "index";
        }catch (UnknownAccountException e){ //用户名不存在
            model.addAttribute("msg","用户名错误!");
            return "login";
        }catch (IncorrectCredentialsException e){ //密码不存在
            model.addAttribute("msg","密码错误!");
            return "login";
        }
    }

    @RequestMapping("/noauth")
    @ResponseBody
    public String unAuthorized(){
        return "未授权无法访问此页面!";
    }
}
