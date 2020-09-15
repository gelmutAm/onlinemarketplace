package com.epam.marketplace.servlets.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/marketplace/user/*")
public class AuthenticationFilter implements Filter {

    private ServletContext servletContext;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        servletContext = filterConfig.getServletContext();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        HttpSession httpSession = req.getSession(false);

        if (httpSession == null) {
            resp.sendRedirect(req.getContextPath() + "/marketplace");
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }
}
