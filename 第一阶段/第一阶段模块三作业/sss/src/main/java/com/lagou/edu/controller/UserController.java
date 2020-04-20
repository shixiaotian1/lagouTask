package com.lagou.edu.controller;

import com.lagou.edu.pojo.User;
import com.lagou.edu.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private ResumeService resumeService;

    /**
     * 向用户登录页面跳转
     */
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView toLogin(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return  modelAndView;
    }

    /**
     * 用户登录
     * @param user
     * @param model
     * @param session
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(User user, Model model, HttpSession session){
        //获取用户名和密码
        String username=user.getUsername();
        String password=user.getPassword();
        //些处横板从数据库中获取对用户名和密码后进行判断
        if(username!=null&&username.equals("admin")&&password!=null&&password.equals("admin")){
            //将用户对象添加到Session中
            session.setAttribute("USER_SESSION",user);
            //重定向到主页面的跳转方法
            return "redirect:main";
        }
        model.addAttribute("msg","用户名或密码错误，请重新登录！");
        return "login";
    }

    @RequestMapping(value = "/main")
    public ModelAndView toMain() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("main");
        return modelAndView;
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session){
        //清除session
        session.invalidate();
        //重定向到登录页面的跳转方法
        return "redirect:login";
    }

    @RequestMapping(value = "/insert")
    public ModelAndView toInsert(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("insert");
        return modelAndView;
    }
}
