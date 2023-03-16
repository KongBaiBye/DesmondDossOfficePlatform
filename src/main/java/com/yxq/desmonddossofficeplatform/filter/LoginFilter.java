package com.yxq.desmonddossofficeplatform.filter;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * @author Qiang
 */
public class LoginFilter implements Filter {
    /**
     * 登录页面uri
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //登录页面URI
        String loginUri ="/login.html";
        //管理员放行资源URI
        String managerUri ="/page/manager";
        //医生放行资源URI
        String doctorUri ="/page/doctor";
        //用户放行资源URI
        String userUri ="/page/user";
        //公共资源放行资源URI
        String commonUri ="/page/common";
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession(false);
        //当前资源URI
        String uri = req.getRequestURI();
        if(uri.startsWith("/druid")){
            chain.doFilter(req,resp);
        }else if (session != null){
            String username = (String) session.getAttribute("username");
            String roleName = (String) session.getAttribute("roleName");
            if (username==null){
                if (loginUri.equals(uri)){
                    //放行
                    chain.doFilter(req,resp);
                }else {
                    resp.sendRedirect(loginUri);
                }
            }else {
                if ("管理员".equals(roleName)){
                    if (uri.startsWith(managerUri)||uri.startsWith(commonUri)){
                        chain.doFilter(req,resp);
                    }else {
                        resp.sendRedirect(loginUri);
                    }
                }else if ("医生".equals(roleName)){
                    if (uri.startsWith(doctorUri)||uri.startsWith(commonUri)||uri.startsWith("/page/manager/dr")||uri.startsWith("/page/manager/add")|| uri.startsWith("/page/manager/ed")){
                        chain.doFilter(req,resp);
                    }else {
                        resp.sendRedirect(loginUri);
                    }
                }else {
                    if (uri.startsWith(userUri)||uri.startsWith(commonUri)){
                        chain.doFilter(req,resp);
                    }else {
                        resp.sendRedirect(loginUri);
                    }
                }
            }
        }else {
            if (loginUri.equals(uri)){
                chain.doFilter(req,resp);
            }else {
                resp.sendRedirect(loginUri);
            }
        }

    }
}
