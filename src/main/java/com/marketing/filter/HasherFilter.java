package com.marketing.filter;

import com.marketing.utils.Servlets;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@WebFilter(servletNames = {
        Servlets.REGISTER,
        Servlets.LOGIN
})
public class HasherFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String passwd = request.getParameter("password");
        String hashedPasswd;
        try {
            hashedPasswd = hashPasswd(passwd).substring(0, 49);
            request.setAttribute("password", hashedPasswd);
        } catch (NoSuchAlgorithmException e) {
            // TODO handle hash error
        }
        doFilter(servletRequest, servletResponse, filterChain);
    }

    private String hashPasswd(String passwd) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(passwd.getBytes());
        return new String(messageDigest.digest());
    }
}
