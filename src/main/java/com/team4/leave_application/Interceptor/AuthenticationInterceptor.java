package com.team4.leave_application.Interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws IOException {
        System.out.println("Intercepting: " + request.getRequestURI());
        HttpSession session = request.getSession();

        if (session.getAttribute("username") != null)
            return true;
        String[] splitURI = request.getRequestURI().split("/");

        if (splitURI[splitURI.length - 1].equals("login"))
            return true;

        response.sendRedirect("/login");
        //Use ("/login") for an absolute path from the root of the application.
        //Use ("login") for a path relative to the current mapping.
        return false;
    }

    @Override
    public void postHandle( HttpServletRequest request ,
                            HttpServletResponse response , Object handler, ModelAndView modelAndView) {

    }
    @Override
    public void afterCompletion( HttpServletRequest request ,
                                 HttpServletResponse response , Object handler, Exception ex) {

    }
}
