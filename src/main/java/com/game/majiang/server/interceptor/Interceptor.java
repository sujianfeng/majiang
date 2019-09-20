package com.game.majiang.server.interceptor;

import com.game.majiang.base.entity.Manager;
import com.game.majiang.constants.ConfigConstants;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Administrator on 2016/8/28.
 */
public class Interceptor implements HandlerInterceptor {
    private final Logger logger = Logger.getLogger(Interceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        //logger.info("在请求处理之前进行调用（Controller方法调用之前）");
        logger.info("request.getRequestURI()-----------" + request.getRequestURI());
        String uri = request.getRequestURI();
        if (uri.startsWith("/majiang/")) {
            if (!(uri.indexOf("login") > 0)) {
                return checkSession(request, response);
            }
        }
        return true;// 只有返回true才会继续向下执行，返回false取消当前请求
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        //logger.info("请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        //logger.info("在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）");
    }

    private boolean checkSession(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Manager manager = (Manager) request.getSession().getAttribute(ConfigConstants.MANAGER_KEY);
        if (manager == null) {
            logger.info("session中无用户" + request.getContextPath());
            request.setCharacterEncoding("UTF8");
            response.setCharacterEncoding("UTF8");
            PrintWriter out = response.getWriter();
            StringBuilder builder = new StringBuilder();
            builder.append("<script type=\"text/javascript\">");
            //builder.append("alert('Web page expired, please login again!');");
            builder.append("window.location.href='");
            builder.append(request.getContextPath() + "/login.html");
            builder.append("';");
            builder.append("</script>");
            out.print(builder.toString());
            return false;
        }
        return true;
    }

}