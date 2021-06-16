package com.ball.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class SessionInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {

//        log.info("preHandle+++++++++++++++++++++++++++++++++++++++++++++");

        HttpSession session = request.getSession(false);
        Cookie[] cookies = request.getCookies();

        if(session != null && cookies.length >0) {
            // get user session
            Object obj = session.getAttribute("userID");
            // get timer cookie
            Cookie timerCookie = null;
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if ("timerCookie".equals(c.getName())) {
                        timerCookie = c;
                        break;
                    }
                }
            }
            if(obj != null && timerCookie != null) {
                log.info("not null session---------------");
                return true;
            }
        }

        // not session and cookie => /home /login이 경로인지 확인
        String reqURI = request.getRequestURI();
        if (reqURI.equals("/") || reqURI.equals("/user/login")|| reqURI.equals("/user/create")) {
            return true;
        }

        response.sendRedirect(request.getContextPath() + "/user/login");
        return false;
    }
}
