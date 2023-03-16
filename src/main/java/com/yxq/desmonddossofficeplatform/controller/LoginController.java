package com.yxq.desmonddossofficeplatform.controller;


import cn.hutool.extra.servlet.ServletUtil;
import com.yxq.desmonddossofficeplatform.service.LoginService;
import com.yxq.desmonddossofficeplatform.utils.RedisUtils;
import com.yxq.desmonddossofficeplatform.utils.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Author: yxq
 * @Date: 2023/01/14 20:27
 */
@RestController
@RequestMapping("/login")
@SessionAttributes("username")
public class LoginController {
    @Resource
    LoginService loginService;

    //url：http://localhost:8080/page/loginPage/login.html
    @RequestMapping("log")
    public ResultData selectPassword(HttpServletRequest req, HttpServletResponse resp, HttpSession session) {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String captcha = req.getParameter("captcha");
        //获取用户ip地址
        String ip = ServletUtil.getClientIP(req, null);
        //从redis中拿到验证码
        String captcha1 = RedisUtils.getString("captcha" + ip);
        if (! captcha1.equals(captcha) ){
            return ResultData.success("验证码错误");
        }
        HttpSession session1 = req.getSession(false);
        session1.setAttribute("username", username);
        session1.setMaxInactiveInterval(600);
        Cookie cookie1 = new Cookie("username", username);
        cookie1.setPath("/");
        cookie1.setMaxAge(600);
        resp.addCookie(cookie1);
        Cookie cookie = new Cookie("JSESSIONID", session1.getId());
        cookie.setPath("/");
        cookie.setMaxAge(600);
        resp.addCookie(cookie);
        return loginService.selectPassword(username, password, captcha, session);
    }

    /**
     * 退出登录
     *
     * @return
     */
    @RequestMapping("/loginOut")
    public ResultData loginOut(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        Object username = session.getAttribute("username");
        if (session != null) {
            session.invalidate();
            Cookie cookie = new Cookie("JSESSIONID", "");
            resp.addCookie(cookie);
        }
        return loginService.loginOut((String) username);
    }
}
