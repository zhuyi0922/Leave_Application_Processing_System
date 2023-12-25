package com.team4.leave_application.Interceptor;

import com.team4.leave_application.Model.Role;
import com.team4.leave_application.Model.UserSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler)
            throws IOException{
        System.out.println("Intercepting " + request.getRequestURI());

        String uri = request.getRequestURI();
        if (uri.startsWith("/css/") || uri.startsWith("/images/")) {
            return true;
        }

        if (uri.equalsIgnoreCase("/") || uri.equalsIgnoreCase("/home")
                || uri.equalsIgnoreCase("/login")
                || uri.equalsIgnoreCase("/about")) {
            return true;
        }

        if (uri.startsWith("/login/")) {
            return true;
        }

        HttpSession session = request.getSession();
        UserSession userSession = (UserSession) session.getAttribute("usession");
        if (userSession == null) {
            // No username, meaning not logged in yet
            // Redirect to login page
            response.sendRedirect("/login");
            return false;
        }
        // still not implement if the user is not staff or admin
        // refer to demo.
        List<String> userRoles = userSession.getUser().getRoleSet().stream().map(x -> x.getName()).toList();
        if (uri.startsWith("/admin") && !userRoles.contains("admin")) {
            response.sendRedirect("/no-accessability");
        }

        if (uri.startsWith("/manager") && !userRoles.contains("manager")) {
            response.sendRedirect("/no-accessability");
        }
        return true;
    }
}
